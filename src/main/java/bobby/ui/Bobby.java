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
     * Start message displayed when the bot starts
     */
    private static final String START = "Hello! I'm Bobby\n"
            + "What can I do for you?\n";

    /**
     * Exit message when the bot terminates
     */
    private static final String EXIT = "Bye. Hope to see you again soon!";
    /**
     * Retry message displayed when the bot cannot recognize an action.
     */
    private static final String RETRY = "HELP!! I do not recognise this action as of now.\n"
            + "You can try: todo xxx, event xxx /from xxx /to xxx, "
            + "deadline xxx /by xxx, unmark x, mark x, list, bye\n";
    /**
     * A {@link DateTimeFormatter} used to format and parse date-time strings
     * in the pattern "yyyy-MM-dd HHmm".
     */
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

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
    /**
     * Gets the start message to display when Bobby starts.
     *
     * @return The start message.
     */
    public static String getStartMsg() {
        return START;
    }

    /**
     * Constructs a Task object from a string description.
     * The string format is expected to match the task types (ToDo, Deadline, or Event)
     * and should include details such as task type, status, description, and time.
     *
     * @param desc The string description of the task.
     * @return A Task object corresponding to the description, or null if input is invalid.
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
     * Checks if a todo task with the given description and due date already exists in the task list.
     * It compares both the description (case-insensitive).
     *
     * @param description The description of the todo task to search for.
     * @return {@code true} if a task with the same description, {@code false} otherwise.
     */
    private static boolean isDuplicateToDo(String description) {
        for (int x = 0; x < TaskList.getSize(); x++) {
            Task t = TaskList.get(x);
            if (t.description.equalsIgnoreCase(description)) {
                return true;
            }
        }
        return false;
    }
    /**
     * Checks if a deadline task with the given description and due date already exists in the task list.
     * It compares both the description (case-insensitive) and the due date.
     *
     * @param description The description of the deadline task to search for.
     * @param by The due date of the deadline task in string format.
     * @return {@code true} if a task with the same description and due date is found, {@code false} otherwise.
     */
    private static boolean isDuplicateDeadline(String description, LocalDateTime by) {
        for (int x = 0; x < TaskList.getSize(); x++) {
            Task t = TaskList.get(x);
            if (t.description.equalsIgnoreCase(description)
                    && t.getBy().isEqual(by)) {
                return true;
            }
        }
        return false;
    }
    /**
     * Checks if a Event task with the given description and due date already exists in the task list.
     * It compares both the description (case-insensitive) and the duration.
     *
     * @param description The description of the deadline task to search for.
     * @param from The start date of the event task in string format.
     * @param by The end date of the event task in string format.
     * @return {@code true} if a task with the same description and duration is found, {@code false} otherwise.
     */
    private static boolean isDuplicateEvent(String description, LocalDateTime from, LocalDateTime by) {
        for (int x = 0; x < TaskList.getSize(); x++) {
            Task t = TaskList.get(x);
            if (t.description.equalsIgnoreCase(description)
                    && t.getBy().isEqual(by)
                    && t.getFrom().isEqual(from)) {
                return true;
            }
        }
        return false;
    }
    /**
     * Handles the "bye" command by saving tasks and returning the exit message.
     *
     * @param desc The user input after the "bye" command.
     * @return The exit message or retry message.
     */
    private static String handleBye(String desc) {
        if (desc == null) {
            Storage.writeToFile(taskList);
            System.exit(0);
            return EXIT;
        } else {
            return RETRY;
        }
    }

    /**
     * Handles the "list" command by returning the current list of tasks.
     *
     * @param desc The user input after the "list" command.
     * @return The list of tasks or the retry message.
     */
    private static String handleList(String desc) {
        if (desc == null) {
            return TaskList.print_list();
        } else {
            return RETRY;
        }
    }
    /**
     * Handles the "delete" command by deleting the specified task from the task list.
     *
     * @param desc The user input specifying which task to delete.
     * @return The response after the task is deleted or a retry message.
     */
    private static String handleDelete(String desc) {
        try {
            int currIndex = Integer.parseInt(desc);
            String response = TaskList.deleteTask(currIndex);
            Storage.writeToFile(taskList);
            return response;
        } catch (Exception e) {
            return RETRY;
        }
    }

    /**
     * Handles the "find" command by finding tasks with the specified keyword.
     *
     * @param desc The user input specifying the keyword to search for.
     * @return The response after finding matching tasks or a retry message.
     */
    private static String handleFind(String desc) {
        if (desc == null) {
            return RETRY;
        } else {
            return TaskList.findTask(desc.trim());
        }
    }

    /**
     * Handles the "mark" command by marking the specified task as done.
     *
     * @param desc The user input specifying which task to mark.
     * @return The response after marking the task as done or a retry message.
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
        String response = currTask.markAsDone();
        Storage.writeToFile(taskList);
        return response;
    }

    /**
     * Handles the "unmark" command by unmarking the specified task as not done.
     *
     * @param desc The user input specifying which task to unmark.
     * @return The response after unmarking the task or a retry message.
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
        String response = currTask.unMark();
        Storage.writeToFile(taskList);
        return response;
    }

    /**
     * Handles the "todo" command by adding a new ToDo task to the task list.
     *
     * @param desc The user input specifying the ToDo task description.
     * @return The response after adding the task or an error message if invalid.
     */
    private static String handleTodo(String desc) {
        if (desc == null || desc.trim().isEmpty()) {
            return "OOPS!!! The description of a todo is empty. (todo xxx)\n";
        }
        if (isDuplicateToDo(desc.trim())) {
            return "It seems like this task has already been added.";
        } else {
            ToDos currToDo = new ToDos(desc.trim());
            TaskList.add_task(currToDo);
            Storage.writeToFile(taskList);
            return "Got it. I've added this task:\n"
                    + currToDo + "\n"
                    + "Now you have " + TaskList.getSize() + " tasks in the list.\n";
        }
    }

    /**
     * Handles the "deadline" command by adding a new Deadline task to the task list.
     *
     * @param desc The user input specifying the Deadline task description and date/time.
     * @return The response after adding the task or an error message if invalid.
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
        try {
            LocalDateTime by = LocalDateTime.parse(details[1].trim(), FORMATTER);
            if (isDuplicateDeadline(details[0].trim(), by)) {
                return "It seems like this task has already been added.";
            } else {
                Deadlines currDeadline = new Deadlines(details[0].trim(), by);
                TaskList.add_task(currDeadline);
                Storage.writeToFile(taskList);
                return "Got it. I've added this task:\n"
                        + currDeadline + "\n"
                        + "Now you have " + TaskList.getSize() + " tasks in the list.\n";
            }
        } catch (DateTimeParseException e) {
            return errorMsg;
        }
    }

    /**
     * Handles the "event" command by adding a new Event task to the task list.
     *
     * @param desc The user input specifying the Event task description and date/time range.
     * @return The response after adding the task or an error message if invalid.
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
        try {
            LocalDateTime from = LocalDateTime.parse(duration[0].trim(), FORMATTER);
            LocalDateTime to = LocalDateTime.parse(duration[1].trim(), FORMATTER);
            if (isDuplicateEvent(details[0].trim(), from, to)) {
                return "It seems like this task has already been added.";
            } else if (from.isAfter(to)) {
                return "Please ensure the start date & time is before the end date & time!";
            } else {
                assert(from.isBefore(to) || from.isEqual(to));
                Events currEvent = new Events(details[0].trim(), from, to);
                TaskList.add_task(currEvent);
                Storage.writeToFile(taskList);
                return "Got it. I've added this task:\n"
                        + currEvent + "\n"
                        + "Now you have " + TaskList.getSize() + " tasks in the list.\n";
            }
        } catch (DateTimeParseException e) {
            return errorMsg;
        }
    }

    /**
     * Determines and executes the appropriate action based on user input.
     *
     * @param action The type of action to perform.
     * @param desc The user input for the command.
     * @return The response after executing the action.
     */
    public static String checkAction(ActionType action, String desc) {
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
