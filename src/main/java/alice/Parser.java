package alice;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Parses user commands and performs corresponding actions.
 * Manages interaction with the user interface, task list, and storage.
 */
public class Parser {
    private Ui ui;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
    private TaskList list;
    private Storage storage;

    /**
     * Constructs a Parser instance with a specified storage path.
     * Initializes the UI, task list, and storage components.
     *
     * @param path The file path to the storage location for tasks.
     */
    public Parser(String path) {
        this.ui = new Ui();
        this.list = new TaskList();
        this.storage = new Storage(path);
    }

    /**
     * Constructs a Parser instance with specified UI, task list, and storage objects.
     *
     * @param ui The UI object for user interactions and feedback.
     * @param list The task list to manage tasks.
     * @param storage The storage object to handle saving and loading tasks.
     */
    public Parser(Ui ui, TaskList list, Storage storage) {
        this.ui = ui;
        this.list = list;
        this.storage = storage;
    }

    /**
     * Enum representing the types of commands that the parser can handle.
     */
    public enum CommandType {
        BYE, LIST, MARK, UNMARK, DELETE,
        TODO, EVENT, DEADLINE, FIND, INVALID
    }

    /**
     * Parses the user input and returns the corresponding command type.
     *
     * @param input The user input string to parse.
     * @return The CommandType corresponding to the input string.
     */
    public static CommandType parseCommand(String input) {
        switch (input) {
        case "bye":
            return CommandType.BYE;
            // Fallthrough
        case "list":
            return CommandType.LIST;
            // Fallthrough
        case "mark":
            return CommandType.MARK;
            // Fallthrough
        case "unmark":
            return CommandType.UNMARK;
            // Fallthrough
        case "delete":
            return CommandType.DELETE;
            // Fallthrough
        case "todo":
            return CommandType.TODO;
            // Fallthrough
        case "event":
            return CommandType.EVENT;
            // Fallthrough
        case "deadline":
            return CommandType.DEADLINE;
            // Fallthrough
        case "find":
            return CommandType.FIND;
            // Fallthrough
        default:
            return CommandType.INVALID;
            // Fallthrough
        }
    }

    /**
     * Parses and performs the action based on user input.
     * Executes actions such as listing tasks, marking/unmarking tasks, deleting tasks, and adding tasks.
     *
     * @param input The user input string containing the command and parameters.
     * @return True if the application should continue running, false if it should exit.
     */
    public boolean performAction(String input) {
        String[] parts = input.split(" ", 2);
        String verb = parts[0];
        CommandType command = parseCommand(verb);

        switch (command) {
        case BYE:
            exit();
            return false;
            // Fallthrough
        case LIST:
            listTasks();
            return true;
            // Fallthrough
        case MARK:
            handleMark(input);
            return true;
            // Fallthrough
        case UNMARK:
            handleUnmark(input);
            return true;
            // Fallthrough
        case DELETE:
            handleDelete(input);
            return true;
            // Fallthrough
        case TODO:
            handleTodo(input);
            return true;
            // Fallthrough
        case EVENT:
            handleEvent(input);
            return true;
            // Fallthrough
        case DEADLINE:
            handleDeadline(input);
            return true;
            // Fallthrough
        case FIND:
            handleFind(input);
            return true;
            // Fallthrough
        case INVALID:
            handleInvalid(input);
            return true;
            // Fallthrough
        default:
            return false;
            // Fallthrough
        }
    }

    /**
     * Parses a string representation of a time and returns the corresponding LocalDateTime object.
     *
     * @param time The string representation of the time in "dd-MM-yyyy HHmm" format.
     * @return The LocalDateTime object representing the parsed time, or null if the format is incorrect.
     */
    public LocalDateTime setTime(String time) {
        try {
            return LocalDateTime.parse(time, formatter);
        } catch (Exception e) {
            System.out.println("Wrong format for the time: " + e.getMessage());
            return null;
        }
    }

    /**
     * Lists all tasks in the task list and displays them to the user.
     */
    public void listTasks() {
        ui.listTasks(list);
    }

    /**
     * Greets the user by calling the appropriate method in the UI.
     */
    private void greet() {
        ui.greet();
    }

    /**
     * Exits the application, saves the current task list to storage, and performs any necessary cleanup.
     */
    private void exit() {
        ui.exit();
        storage.save(list);
    }

    /**
     * Marks a task as done based on the user input.
     *
     * @param input The user input containing the task number to mark as done.
     */
    private void handleMark(String input) {
        int num = Integer.parseInt(input.split(" ")[1]);
        list.markTask(num);
        storage.save(list);
    }

    /**
     * Marks a task as undone based on the user input.
     *
     * @param input The user input containing the task number to mark as undone.
     */
    private void handleUnmark(String input) {
        int num = Integer.parseInt(input.split(" ")[1]);
        list.unmarkTask(num);
        storage.save(list);
    }

    /**
     * Deletes a task from the task list based on the user input.
     *
     * @param input The user input containing the task number to delete.
     */
    private void handleDelete(String input) {
        int num = Integer.parseInt(input.split(" ")[1]);
        list.delete(num);
        storage.save(list);
    }

    /**
     * Adds a new todo task based on the user input.
     *
     * @param input The user input containing the description of the todo task.
     */
    private void handleTodo(String input) {
        try {
            String description = input.split(" ", 2)[1];
            Todo todo = new Todo(description);
            list.addToList(todo);
            storage.save(list);
        } catch (Exception e) {
            ui.todoMsg();
        }
    }

    /**
     * Adds a new event task based on the user input.
     *
     * @param input The user input containing the description, start time, and end time of the event.
     */
    private void handleEvent(String input) {
        try {
            String[] detail = input.split(" ", 2)[1].split("/from");
            String description = detail[0].trim();
            String[] time = detail[1].trim().split("/to");
            String start = time[0].trim();
            String end = time[1].trim();
            Event event = new Event(description, setTime(start), setTime(end));
            list.addToList(event);
            storage.save(list);
        } catch (Exception e) {
            ui.eventMsg();
        }
    }

    /**
     * Adds a new deadline task based on the user input.
     *
     * @param input The user input containing the description and deadline time of the deadline task.
     */
    private void handleDeadline(String input) {
        try {
            String[] detail = input.split(" ", 2)[1].split("/by");
            String description = detail[0].trim();
            String time = detail[1].trim();
            Deadline deadline = new Deadline(description, setTime(time));
            list.addToList(deadline);
            storage.save(list);
        } catch (Exception e) {
            ui.deadlineMsg();
        }
    }

    /**
     * Handles invalid commands and provides feedback to the user.
     *
     * @param input The invalid user input.
     */
    private void handleInvalid(String input) {
        ui.invalidMsg();
    }

    /**
     * Handles the "find" command by searching for tasks that match the specified keyword.
     *
     * @param input The user input string containing the "find" command followed by the keyword
     *              to search for in the task list.
     *              The input format should be "find [keyword]".
     */
    public void handleFind(String input) {
        String keyword = input.split(" ", 2)[1].trim();
        TaskList findList = list.findTask(keyword);
        if (findList.getSize() == 0) {
            ui.noFindMsg();
        } else {
            ui.findMsg(findList);
        }
    }
}
