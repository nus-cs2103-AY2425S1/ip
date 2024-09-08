package alice.utility;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import alice.task.Deadline;
import alice.task.Event;
import alice.task.Todo;

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
        case "list":
            return CommandType.LIST;
        case "mark":
            return CommandType.MARK;
        case "unmark":
            return CommandType.UNMARK;
        case "delete":
            return CommandType.DELETE;
        case "todo":
            return CommandType.TODO;
        case "event":
            return CommandType.EVENT;
        case "deadline":
            return CommandType.DEADLINE;
        case "find":
            return CommandType.FIND;
        default:
            return CommandType.INVALID;
        }
    }

    /**
     * Parses and performs the action based on user input.
     * Executes actions such as listing tasks, marking/unmarking tasks, deleting tasks, and adding tasks.
     *
     * @param input The user input string containing the command and parameters.
     * @return True if the application should continue running, false if it should exit.
     */
    public String performAction(String input) {
        String[] parts = input.split(" ", 2);
        String verb = parts[0];
        CommandType command = parseCommand(verb);

        switch (command) {
        case BYE:
            storage.save(list);
            return ui.exitMsg();
        case LIST:
            return ui.listTasks(list);
        case MARK:
            try {
                int numMark = Integer.parseInt(input.split(" ")[1]);
                return list.markTask(numMark);
            } catch (Exception e) {
                return ui.invalidNumMsg();
            }
        case UNMARK:
            try {
                int numUnmark = Integer.parseInt(input.split(" ")[1]);
                return list.unmarkTask(numUnmark);
            } catch (Exception e) {
                return ui.invalidNumMsg();
            }
        case DELETE:
            try {
                int numDelete = Integer.parseInt(input.split(" ")[1]);
                return list.delete(numDelete);
            } catch (Exception e) {
                return ui.invalidNumMsg();
            }
        case TODO:
            try {
                String description = input.split(" ", 2)[1];
                Todo todo = new Todo(description);
                return list.addToList(todo);
            } catch (Exception e) {
                return ui.todoMsg();
            }
        case EVENT:
            try {
                String[] detail = input.split(" ", 2)[1].split("/from");
                String description = detail[0].trim();
                String[] time = detail[1].trim().split("/to");
                String start = time[0].trim();
                String end = time[1].trim();
                Event event = new Event(description, setTime(start), setTime(end));
                return list.addToList(event);
            } catch (Exception e) {
                return ui.eventMsg();
            }
        case DEADLINE:
            try {
                String[] detail = input.split(" ", 2)[1].split("/by");
                String description = detail[0].trim();
                String time = detail[1].trim();
                Deadline deadline = new Deadline(description, setTime(time));
                return list.addToList(deadline);
            } catch (Exception e) {
                return ui.deadlineMsg();
            }
        case FIND:
            String keyword = input.split(" ", 2)[1].trim();
            TaskList findList = list.findTaskByKeyword(keyword);
            if (findList.getTaskCount() == 0) {
                return ui.noFindMsg();
            } else {
                return ui.findMsg(findList);
            }
        case INVALID:
            return ui.invalidMsg();
        default:
            return ui.invalidMsg();
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
}
