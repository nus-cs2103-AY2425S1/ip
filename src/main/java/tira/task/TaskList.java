package tira.task;

import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import tira.TiraException;
import tira.Ui;

/**
 * Manages list of tasks, including adding, deleting, marking, unmarking.
 */
public class TaskList {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private ArrayList<Task> tasks;
    private final PrintWriter printer = new PrintWriter(System.out);
    private Ui ui;

    /**
     * Creates an empty list of tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
        this.ui = new Ui();
    }

    /**
     * Initialises the taskList using an ArrayList of tasks.
     *
     * @param tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
        this.ui = new Ui();
    }

    /**
     * Getter method to get the tasks in taskList.
     *
     * @return The list of tasks
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Marks the numbered task in the taskList.
     *
     * @param  commandSplitBySpace split using " "
     * @throws TiraException Custom Tira exception class
     */
    public String markTask(String[] commandSplitBySpace) throws TiraException {
        assert Integer.valueOf(commandSplitBySpace[1]) > 0 : "Task number should be more than 0";
        if (commandSplitBySpace.length < 2) {
            throw new TiraException("MRAW?? Please give task number.");
        }
        int currNum = Integer.parseInt(commandSplitBySpace[1]) - 1;
        if (currNum > tasks.size() - 1 || currNum < 0) {
            throw new TiraException("MRAW?? There's less or more task in the list. Please review the task number");
        }
        tasks.get(currNum).markStatus();
        Task currTask = tasks.get(currNum);
        ui.showMarkTask(currTask);
        return ui.getOutMessage();
    }

    /**
     * Unmarks the numbered task in the taskList.
     * @param  commandSplitBySpace split using " "
     * @throws TiraException Custom Tira exception class
     */

    public String unmarkTask(String[] commandSplitBySpace) throws TiraException {
        assert Integer.valueOf(commandSplitBySpace[1]) > 0 : "Task number should be more than 0";
        if (commandSplitBySpace.length < 2) {
            throw new TiraException("MRAW?? Please give task number.");
        }
        int currNum = Integer.parseInt(commandSplitBySpace[1]) - 1;
        if (currNum > tasks.size() - 1 || currNum < 0) {
            throw new TiraException("MRAW?? There's less or more task in the list. Please review the task number");
        }
        tasks.get(currNum).unmarkStatus();
        Task currTask = tasks.get(currNum);
        ui.showUnmarkTask(currTask);
        return ui.getOutMessage();
    }

    /**
     * Adds the todo task into the TaskList
     *
     * @param command The command inputted by user
     * @param  commandSplitBySpace command split using " "
     * @throws TiraException Custom Tira Exception
     */
    public String addToDo(String command, String[] commandSplitBySpace) throws TiraException {
        if (commandSplitBySpace.length < 2) {
            System.out.println("There is an addToDo exception");
            throw new TiraException("MRAW?? Please specify the todo task.");
        }
        String description = "";
        for (int i = 1; i < commandSplitBySpace.length; i++) {
            description += (commandSplitBySpace[i]);
            if (i != commandSplitBySpace.length - 1) {
                description += " ";
            }
        }
        Task newTask = new ToDo(description);
        tasks.add(newTask);
        ui.showAddTask(newTask, tasks.size());
        return ui.getOutMessage();
    }

    /**
     * Adds deadline task to the task list.
     *
     * @param command inputted by the user
     * @param commandSplitBySpace Command separated by the space
     * @throws TiraException custom Tira exception
     */
    public String addDeadline(String command, String[] commandSplitBySpace) throws TiraException {
        if (commandSplitBySpace.length <= 1) {
            throw new TiraException("MRAW?? Please specify deadline task and the deadline date.");
        } else if (commandSplitBySpace[1].startsWith("/")) {
            throw new TiraException("MRAW?? Please input task this format: deadline DESCRIPTION /by yyyy-MM-dd");
        }
        String[] commandSplitBySlash = command.split("/");
        if (commandSplitBySlash.length < 2) {
            throw new TiraException("MRAW?? Please specify the deadline date");
        }
        try {
            LocalDate endDate = LocalDate.parse(extractAfterWord(commandSplitBySlash[1], "by").trim(),
                    DATE_FORMATTER);
            Task deadlineTask = new Deadline(extractAfterWord(commandSplitBySlash[0], "deadline"), endDate);
            tasks.add(deadlineTask);
            ui.showAddTask(deadlineTask, tasks.size());
            return ui.getOutMessage();
        } catch (DateTimeParseException e) {
            throw new TiraException("MRAW?? Incorrect date format. Please enter in yyyy-MM-dd");
        }

    }

    /**
     * Adds event task to the task list.
     *
     * @param command inputted by the user
     * @param commandSplitBySpace separated by the space
     * @throws TiraException custom Tira exception
     */
    public String addEvent(String command, String[] commandSplitBySpace) throws TiraException {
        if (commandSplitBySpace.length <= 1) {
            throw new TiraException("MRAW?? Please specify event name and timing.");
        } else if (commandSplitBySpace[1].startsWith("/")) {
            throw new TiraException("MRAW?? Please provide task in this format: event DESCRIPTION /from yyyy-MM-dd"
                    + "/to yyyy-MM-dd" );
        }
        String[] commandSplitBySlash = command.split("/");
        if (commandSplitBySlash.length <= 2) {
            throw new TiraException("MRAW?? Not enough details. Please provide:"
                    + " event task, start date and end date");
        }
        try {
            LocalDate startDate = LocalDate.parse(extractAfterWord(commandSplitBySlash[1], "from"),
                    DATE_FORMATTER);
            LocalDate endDate = LocalDate.parse(extractAfterWord(commandSplitBySlash[2], "to"),
                    DATE_FORMATTER);
            Task eventTask = new Event(extractAfterWord(commandSplitBySlash[0], "event"), startDate, endDate);
            tasks.add(eventTask);
            ui.showAddTask(eventTask, tasks.size());
            return ui.getOutMessage();
        } catch (DateTimeParseException e) {
            throw new TiraException("MRAW?? Incorrect date format. Please enter in yyyy-MM-dd");
        }
    }
    /**
     * Deletes task of specific number in the task list.
     *
     * @param  commandSplitBySpace separated by the space
     * @throws TiraException custom Tira exception
     */
    public String delete(String[] commandSplitBySpace) throws TiraException {
        assert Integer.valueOf(commandSplitBySpace[1]) > 0 : "Task number should be more than 0";
        if (commandSplitBySpace.length <= 1) {
            throw new TiraException("MRAW?? Please provide task number to delete");
        }
        int taskNumberToDelete = Integer.parseInt(commandSplitBySpace[1]);
        if (taskNumberToDelete > tasks.size() || taskNumberToDelete < 0) {
            throw new TiraException("MRAW?? There's less or more task in the list. Please check again");
        }
        Task taskToRemove = tasks.get(taskNumberToDelete - 1);
        tasks.remove(taskNumberToDelete - 1);
        ui.showDelete(taskToRemove, tasks.size());
        return ui.getOutMessage();
    }

    /**
     * Finds a specific task.
     *
     * @param command user command.
     * @param  commandSplitBySpace User command split by " ".
     * @throws TiraException Custom Tira exception
     */
    public String findTask(String command, String[] commandSplitBySpace) throws TiraException {
        ArrayList<Task> tasksThatMatch = new ArrayList<>();
        String description = "";
        for (int i = 1; i < commandSplitBySpace.length; i++) {
            if (commandSplitBySpace[i].equals("/from") || commandSplitBySpace[i].equals("/by")) {
                break;
            }
            description += (commandSplitBySpace[i]) + " ";
        }
        description = description.trim();
        for (Task task: tasks) {
            String currentTaskDescription = task.getDescription().trim();
            if (description.equals(currentTaskDescription)) {
                tasksThatMatch.add(task);
            }
        }
        if (tasksThatMatch.isEmpty()) {
            ui.showNoMatchingTask();
            return ui.getOutMessage();
        } else {
            ui.showMatchingTasks(tasksThatMatch);
            return ui.getOutMessage();
        }
    }

    /**
     * Extracts the String after a certain word
     * @param text the String that wants to be analysed
     * @param beginWord that the rest of the string needs to be extracted after.
     * @return
     */
    private static String extractAfterWord(String text, String beginWord) {
        int index = text.indexOf(beginWord);
        int startIndex = index + beginWord.length();
        return text.substring(startIndex).trim();
    }
}
