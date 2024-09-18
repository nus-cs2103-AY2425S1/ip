package stelle;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import stelle.exception.*;
import stelle.task.Deadline;
import stelle.task.Event;
import stelle.task.Task;
import stelle.task.ToDo;

/**
 * This class encapsulates chatbot parsing and logic.
 * @author Lee Ze Hao (A0276123J)
 */

public class Parser {
    static final String BYE_COMMAND = "bye";
    static final String LIST_COMMAND = "list";
    static final String MARK_COMMAND = "mark";
    static final String UNMARK_COMMAND = "unmark";
    static final String DELETE_COMMAND = "delete";
    static final String FIND_COMMAND = "find";

    static final String TODO_COMMAND = "todo";
    static final String DEADLINE_COMMAND = "deadline";
    static final String EVENT_COMMAND = "event";

    private static final String DATE_TIME_INPUT_FORMATTER_PATTERN = "yyyy-MM-dd HH:mm";
    private static final String DATE_TIME_OUTPUT_FORMATTER_PATTERN = "d LLLL yyyy HH:mm";
    private static final DateTimeFormatter dateTimeInputFormatter = DateTimeFormatter
            .ofPattern(DATE_TIME_INPUT_FORMATTER_PATTERN);
    private static final DateTimeFormatter dateTimeOutputFormatter = DateTimeFormatter
            .ofPattern(DATE_TIME_OUTPUT_FORMATTER_PATTERN);

    enum TaskType {
        TODO,
        DEADLINE,
        EVENT
    }

    private final String filePath;
    private final TaskList taskList;

    /**
     * Constructor for a ChatLogic class. Also fetches data from the specified file path upon construction.
     * @param filePath The file path used for data storage.
     */
    public Parser(String filePath) {
        this.filePath = filePath;
        this.taskList = new TaskList(this.filePath);
    }

    /**
     * Processes text strings inputted by the user, and calls other functions
     * when appropriate.
     * @param input The text string entered by the user.
     * @return String output of the chatbot.
     * @throws StelleException Throws certain exceptions related to the chatbot.
     */
    public String processInput(String input) throws StelleException, IOException {
        String outputString;

        if (input.equals(BYE_COMMAND)) {
            taskList.writeToFile();
            outputString = returnBye();
            System.exit(0);
        } else if (input.contains(FIND_COMMAND)) {
            outputString = processFindTaskInput(input);
        } else if (input.contains(MARK_COMMAND) || input.contains(UNMARK_COMMAND)) {
            outputString = processMarkUnmarkInput(input);
        } else if (input.equals(LIST_COMMAND)) {
            outputString = listTasks();
        } else if (input.contains(TODO_COMMAND) || input.contains(DEADLINE_COMMAND) || input.contains(EVENT_COMMAND)) {
            outputString = processAddTaskInput(input);
        } else if (input.contains(DELETE_COMMAND)) {
            outputString = processDeleteTaskInput(input);
        } else {
            throw new WrongCommandException();
        }

        return outputString;
    }

    /**
     * Returns the ending response when exiting the program.
     * @return Bye response String.
     */
    private String returnBye() {
        return "Bye.";
    }

    /**
     * Prints the tasks with names that contain the find query entered by user.
     * @param input The text string entered by the user.
     */
    private String processFindTaskInput(String input) {
        String outputString = "";

        String query = input.split(" ", 2)[1];
        ArrayList<Integer> resultList = taskList.find(query, false);

        if (resultList.isEmpty()) {
            outputString = "No tasks with names matching this query!";
            return outputString;
        }

        outputString = outputString + "Here are the matching tasks in your list:";

        for (int i = 0; i < resultList.size(); i++) {
            outputString = outputString + "\n " + (i + 1) + ". " + this.taskList.get(resultList.get(i)).toString();
        }

        return outputString;
    }

    private String processDeleteTaskInput(String input) throws TaskException, IOException {
        String outputString = "";

        String possibleTaskNumString = input.replaceAll("[^\\d.]", "");
        if (possibleTaskNumString.isEmpty()) {
            throw new DeletionNotSpecifiedException();
        }

        int possibleTaskNum = Integer.valueOf(possibleTaskNumString);
        if (possibleTaskNum <= 0 || possibleTaskNum > this.taskList.size()) {
            throw new NoSuchTaskException();
        }

        outputString = outputString
                + "Alright. Removed the task:\n"
                + this.taskList.get(possibleTaskNum - 1).toString();

        assert possibleTaskNum > 0 && possibleTaskNum <= this.taskList.size();
        this.taskList.remove(possibleTaskNum - 1);
        this.taskList.writeToFile();

        return outputString;
    }

    private String processMarkUnmarkInput(String input) throws StelleException, IOException {
        String possibleTaskNumString = input.replaceAll("[^\\d.]", "");
        if (possibleTaskNumString.isEmpty()) {
            throw new MarkNotSpecifiedException();
        }

        int possibleTaskNum = Integer.valueOf(possibleTaskNumString);
        if (input.contains(UNMARK_COMMAND)) {
            return unmarkTask(possibleTaskNum);
        } else if (input.contains(MARK_COMMAND)) {
            return markTask(possibleTaskNum);
        } else {
            throw new WrongCommandException();
        }
    }

    private String processAddTaskInput(String input) throws IOException {
        if (input.isEmpty()) {
            return "I can't add the task if you don't specify a task name.";
        }

        TaskType taskType;
        if (input.contains(TODO_COMMAND)) {
            taskType = TaskType.TODO;
        } else if (input.contains(DEADLINE_COMMAND)) {
            taskType = TaskType.DEADLINE;
        } else if (input.contains(EVENT_COMMAND)) {
            taskType = TaskType.EVENT;
        } else {
            throw new WrongCommandException();
        }

        String outputString = "";

        switch (taskType) {
        case TODO:
            outputString = addToDo(input);
            break;
        case DEADLINE:
            outputString = addDeadline(input);
            break;
        case EVENT:
            outputString = addEvent(input);
            break;
        default:
            break;
        }

        return outputString;
    }

    private String addToDo(String input) throws TaskException, IOException {
        String taskName = input.replace(TODO_COMMAND, "").strip();

        if (!taskList.find(taskName, true).isEmpty()) {
            return "There is already a task with this name though...";
        }
        if (taskName.isEmpty()) {
            throw new ToDoNoDescriptionException();
        }

        this.taskList.add(new ToDo(taskName));
        this.taskList.writeToFile();

        String outputString = "";
        outputString = outputString + "Got it. I've added it as a To Do:";
        outputString = outputString + "\n" + this.taskList.get(taskList.size() - 1).toString();
        outputString = outputString + "\nNow you have " + this.taskList.size() + " tasks in the list.";

        return outputString;
    }

    private String addDeadline(String input) throws TaskException, IOException {
        String noCommandInput = input.replace(DEADLINE_COMMAND, "").strip();
        if (!(noCommandInput.contains("/by"))) {
            throw new NoDateTagException();
        }

        String taskName = noCommandInput.split("/by")[0].strip();
        if (taskName.isEmpty()) {
            throw new DeadlineNoDescriptionException();
        }
        if (!taskList.find(taskName, true).isEmpty()) {
            return "There is already a task with this name though...";
        }

        String dateString = noCommandInput.split("/by")[1].strip();

        // parse date
        LocalDateTime date;
        try {
            date = LocalDateTime.parse(dateString.strip(), dateTimeInputFormatter);
        } catch (Exception e) {
            throw new WrongDateFormatException();
        }

        this.taskList.add(new Deadline(taskName, date));
        this.taskList.writeToFile();

        String outputString = "";
        outputString = outputString + "Got it. I've added it as a Deadline:";
        outputString = outputString + "\n" + this.taskList.get(taskList.size() - 1).toString();
        outputString = outputString + "\nNow you have " + this.taskList.size() + " tasks in the list.";

        return outputString;
    }

    private String addEvent(String input) throws TaskException, IOException {
        String noCommandInput = input.replace(EVENT_COMMAND, "").strip();
        if (!(noCommandInput.contains("/from") && noCommandInput.contains("/to"))) {
            throw new NoDateTagException();
        }

        String taskName = noCommandInput.split("/from")[0].strip();
        if (taskName.isEmpty()) {
            throw new EventNoDescriptionException();
        }

        if (!taskList.find(taskName, true).isEmpty()) {
            return "There is already a task with this name...";
        }
        String fromAndTo = noCommandInput.split("/from")[1].strip();
        String fromDate = fromAndTo.split("/to")[0].strip();
        String toDate = fromAndTo.split("/to")[1].strip();

        // parse dates
        LocalDateTime from;
        LocalDateTime to;
        try {
            from = LocalDateTime.parse(fromDate.strip(), dateTimeInputFormatter);
            to = LocalDateTime.parse(toDate.strip(), dateTimeInputFormatter);
        } catch (Exception e) {
            throw new WrongDateFormatException();
        }

        if (from.isAfter(to)) {
            throw new EventIllogicalDateException();
        }

        this.taskList.add(new Event(taskName, from, to));
        this.taskList.writeToFile();

        String outputString = "";
        outputString = outputString + "Got it. I've added it as an Event:";
        outputString = outputString + "\n" + this.taskList.get(taskList.size() - 1).toString();
        outputString = outputString + "\nNow you have " + this.taskList.size() + " tasks in the list.";

        return outputString;
    }

    /**
     * Marks a certain task (makes it done).
     * @param taskNum The number of the task (ArrayList index + 1) to be marked as done.
     * @return String The output of completing this task.
     * @throws TaskException Throws exceptions related to tasks.
     */
    private String markTask(int taskNum) throws TaskException, IOException {
        if (taskNum <= 0 || taskNum > this.taskList.size()) {
            throw new NoSuchTaskException();
        }

        assert taskNum > 0 && taskNum <= this.taskList.size();
        Task task = this.taskList.get(taskNum - 1);
        task.mark();

        this.taskList.writeToFile();

        return "Nice! I've marked this task as done:\n" + task.toString();
    }

    /**
     * Unmarks a certain task (makes it not done).
     * @param taskNum The number of the task (ArrayList index + 1) to be marked as not done.
     * @throws TaskException Throws exceptions related to tasks.
     */
    private String unmarkTask(int taskNum) throws TaskException, IOException {
        if (taskNum <= 0 || taskNum > this.taskList.size()) {
            throw new NoSuchTaskException();
        }

        Task task = this.taskList.get(taskNum - 1);
        assert taskNum > 0 && taskNum <= this.taskList.size();
        task.unmark();

        this.taskList.writeToFile();

        return "OK, I've marked this task as not done yet:\n" + task.toString();
    }

    /**
     * Reads tasks from local file and lists all Tasks currently stored, with added list numbers.
     * @return String containing list of all currently saved tasks.
     */
    private String listTasks() throws IOException {
        String outputString = "";

        this.taskList.readFromFile();

        outputString = outputString + "Here is everything in your list:";
        for (int i = 0; i < this.taskList.size(); i++) {
            assert i >= 0 && i < this.taskList.size();
            outputString = outputString + "\n " + (i + 1) + ". " + this.taskList.get(i).toString();
        }

        return outputString;
    }
}
