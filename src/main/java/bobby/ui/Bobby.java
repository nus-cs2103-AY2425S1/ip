package bobby.ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Bobby is a chatbot that can manage a list of tasks, ToDos, Deadlines, and Events.
 * It can mark tasks as done, unmark, delete and add tasks.
 */
public class Bobby {

    /**
     * Greeting message displayed when the bot starts
     */
    private static String START = "Hello! I'm Bobby\n"
            + "What can I do for you?\n";

    /**
     * Exit msg when the bot terminates
     */
    private static final String EXIT = "Bye. Hope to see you again soon!";
    private static final String RETRY = "HELP!! I do not recognise this action as of now.\n"
            + "You can try: todo xxx, event xxx /from xxx /to xxx, "
            + "deadline xxx /by xxx, unmark x, mark x, list, bye\n";
    /**
     * TaskList instance to manage the list of task
     */
    private static TaskList taskList;
    /**
     * Enum representing the basic types of tasks that Bobby can handle.
     * T - ToDo
     * D - Deadline
     * E - Event
     */
    private enum SimpleType {
        T, D, E
    }

    /**
     * Enum representation of the action that Bobby can take.
     */
    public enum ActionType {
        bye, list, delete, mark, unmark, todo, deadline, event, retry, find
    }

    public static String getStartMsg() {
        return START;
    }
    /**
     * Constructs a Task object from a string description.
     *
     * @param desc The string description of the task.
     * @return A Task object corresponding to the description,
     *      or null if the input is invalid
     */

    public static Task constructTask(String desc) {
        assert desc != null : "Description cannot be null";
        String[] details = desc.split("/");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
        switch(SimpleType.valueOf(details[0])) {
        case T:
            assert details.length == 3 : "Todo format incorrectly stored";
            Task task = new ToDos(details[2]);
            task.isDone = details[1].equals("1");
            return task;
        case D:
            assert details.length == 4 : "Deadline format incorrectly stored";
            task = new Deadlines(details[2],
                    LocalDateTime.parse(details[3], formatter));
            task.isDone = details[1].equals("1");
            return task;
        case E:
            assert details.length == 5 : "Event format incorrectly stored";
            task = new Events(details[2],
                    LocalDateTime.parse(details[3], formatter),
                    LocalDateTime.parse(details[4], formatter));
            task.isDone = details[1].equals("1");
            return task;
        default:
            System.out.println("GG!! your code is buggy");
            return null;
        }
    }
    /**
     * Handles the "bye" command by saving all tasks to
     * the file and printing the exit message.
     *
     * @throws Exception if there is an issue writing to the file.
     */
    private static String handleBye(String desc) throws Exception {
        if (desc == null) {
            Storage.writeToFile(taskList);
            System.exit(0);
            return EXIT;
        } else {
            return RETRY;
        }
    }

    /**
     * Handles the "list" command by printing the task list
     * and processing the next user action.
     *
     * @throws Exception if there is an issue processing the next action.
     */
    private static String handleList(String desc) {
        if (desc == null) {
            return TaskList.print_list();
        } else {
            return RETRY;
        }
    }

    /**
     * Handles the "delete" command by deleting the specified task from the list.
     *
     * @throws Exception if there is an issue processing the next action.
     */
    private static String handleDelete(String desc) {
        try {
            int currIndex = Integer.parseInt(desc);
            return TaskList.deleteTask(currIndex);
        } catch (Exception e) {
            return RETRY;
        }
    }

    /**
     * Handles the "find" command by finding the task from the list that contains
     * the keywords.
     *
     * @throws Exception if there is an issue processing the next action.
     */
    private static String handleFind(String desc) {
        String keyword = desc;
        if (keyword == null) {
            return RETRY;
        } else {
            return TaskList.findTask(keyword);
        }
    }

    /**
     * Handles the "mark" command by marking the specified task as done.
     *
     * @throws Exception if there is an issue processing the next action.
     */
    private static String handleMark(String desc) {
        Task currTask;
        try {
            int currIndex = Integer.parseInt(desc);
            currTask = TaskList.retrive_task(currIndex);
        } catch (Exception e) {
            return "OOPS! Something's not right! Please input mark x. (e.g mark 1)\n";
        }
        if (currTask == null) {
            return "I can't find this task,"
                    + " please check which task you want to"
                    + " mark by keying in list! \n";
        }
        return currTask.markAsDone();
    }

    /**
     * Handles the "unmark" command by unmarking the specified task as not done.
     *
     * @throws Exception if there is an issue processing the next action.
     */
    private static String handleUnmark(String desc) {
        Task currTask;
        try {
            int currIndex = Integer.parseInt(desc);
            currTask = TaskList.retrive_task(currIndex);
        } catch (Exception e) {
            return "OOPS! Something's not right! Please input unmark x. (e.g unmark 1)\n";
        }
        if (currTask == null) {
            return "I can't find this task,"
                    + " please check which task you want to"
                    + " unmark by keying in list!\n";
        }
        return currTask.unMark();
    }

    /**
     * Handles the "todo" command by adding a new ToDo task to the task list.
     *
     * @throws Exception if there is an issue processing the next action.
     */
    private static String handleTodo(String desc) {
        if (desc == null || desc.trim().isEmpty()) {
            return "OOPS!!! The description of a todo is empty. (todo xxx)\n";
        }
        ToDos currToDo = new ToDos(desc.trim());
        TaskList.add_task(currToDo);
        return "Got it. I've added this task:\n"
                + currToDo.toString() + "\n"
                + "Now you have " + TaskList.getSize() + " tasks in the list.\n";
    }

    /**
     * Handles the "deadline" command by adding a new Deadline task to the task list.
     *
     * @throws Exception if there is an issue processing the next action.
     */
    private static String handleDeadline(String desc) {
        String errorMsg = "OOPS!!! Format of deadline should be "
                + "(deadline xxx /by yyyy-MM-dd HHmm)\n";
        if (desc == null) {
            return errorMsg;
        }
        String[] details = desc.split("/by");
        if (details.length < 2 || details[0].trim().isEmpty() || details[1].trim().isEmpty()) {
            return errorMsg;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        try {
            LocalDateTime by = LocalDateTime.parse(details[1].trim(), formatter);
            Deadlines currDeadline = new Deadlines(details[0].trim(), by);
            TaskList.add_task(currDeadline);
            return "Got it. I've added this task:\n"
                    + currDeadline.toString() + "\n"
                    + "Now you have " + TaskList.getSize() + " tasks in the list.\n";
        } catch (DateTimeParseException e) {
            return errorMsg;
        }
    }

    /**
     * Handles the "event" command by adding a new Event task to the task list.
     *
     * @throws Exception if there is an issue processing the next action.
     */
    private static String handleEvent(String desc) {
        String errorMsg = "OOPS!!! Format of events should be "
                + "(event xxx /from yyyy-MM-dd HHmm /to yyyy-MM-dd HHmm)\n";
        if (desc == null) {
            return errorMsg;
        }
        String[] details = desc.split("/from");
        if (details.length < 2 || details[0].trim().isEmpty()) {
            return errorMsg;
        }
        String[] duration = details[1].split("/to");
        if (duration.length < 2 || duration[0].trim().isEmpty()
                || duration[1].trim().isEmpty()) {
            return errorMsg;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        try {
            LocalDateTime from = LocalDateTime.parse(duration[0].trim(), formatter);
            LocalDateTime to = LocalDateTime.parse(duration[1].trim(), formatter);
            Events currEvent = new Events(details[0].trim(), from, to);
            TaskList.add_task(currEvent);
            return "Got it. I've added this task:\n"
                    + currEvent.toString() + "\n"
                    + "Now you have " + TaskList.getSize() + " tasks in the list.\n";
        } catch (DateTimeParseException e) {
            return errorMsg;
        }
    }

    /**
     * Determines and executes the action based on user input.
     *
     * @param action The type of action to perform, as determined by user input.
     */
    public static String check_action(ActionType action, String desc) throws Exception {
        switch (action) {
        case bye:
            return handleBye(desc);
        case list:
            return handleList(desc);
        case delete:
            return handleDelete(desc);
        case find:
            return handleFind(desc);
        case mark:
            return handleMark(desc);
        case unmark:
            return handleUnmark(desc);
        case todo:
            return handleTodo(desc);
        case deadline:
            return handleDeadline(desc);
        case event:
            return handleEvent(desc);
        case retry:
        default:
            return RETRY;
        }
    }
}
