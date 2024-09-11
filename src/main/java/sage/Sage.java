package sage;

import sage.command.*;
import sage.storage.Storage;
import sage.task.*;
import sage.parser.Parser;
import sage.exception.SageException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The main class for the Sage task management application
 * This class initializes the application components, manages user interactions,
 * and controls the overall flow of the application
 */
public class Sage {
    public static final String DIRECTORY_PATH = "data/";
    public static final String FILE_PATH = DIRECTORY_PATH + "/sage.txt";
    private Storage storage;
    private Ui ui;
    private TaskList tasks;
    private boolean isExit;
    private CommandType commandType;

    private static final String INVALID_TASK_NUMBER = "Invalid task number!!";
    private static final String INVALID_FORMAT = "Please input correctly!!";
    private static final String INVALID_DATE = "What is the date??";
    private static final String EMPTY_TASK_LIST = "No tasks available!!";

    /**
     * Constructs a Sage instance with the specified file path
     * Initializes the UI, storage, parser, and task list.
     * Attempts to load tasks from the file; if unsuccessful, initializes with an empty task list
     *
     */
    public Sage() {
        this.ui = new Ui();
        this.storage = new Storage(FILE_PATH);
        this.isExit = false;
        try {
            this.tasks = new TaskList(this.storage.load());
        } catch (SageException e) {
            this.ui.showErrorMessage(e);
        }
    }

    public String getResponse(String input) {
        try {
            commandType = Parser.parseCommand(input);
            switch (commandType) {

                case BYE :
                    this.exit();
                    return this.ui.showGoodbyeMessage();
                case LIST:
                    return this.ui.showTaskList(tasks);
                case MARK:
                    return markCommand(Parser.parseArgs(CommandType.MARK, input));
                case UNMARK:
                    return unmarkCommand(Parser.parseArgs(CommandType.UNMARK, input));
                case DELETE:
                    return deleteCommand(Parser.parseArgs(CommandType.DELETE, input));
                case FIND:
                    return findCommand(Parser.parseArgs(CommandType.FIND, input));
                case TODO:
                    return toDoCommand(Parser.parseArgs(CommandType.TODO, input));
                case DEADLINE:
                    return deadlineCommand(Parser.parseArgs(CommandType.DEADLINE, input));
                case EVENT:
                    return eventCommand(Parser.parseArgs(CommandType.EVENT, input));
                default:
                    throw new SageException("Sorry, what do you mean? :p");
            }
        } catch (SageException e) {
            return this.ui.showErrorMessage(e);
        }
    }

    public CommandType getCommandType() {
        return commandType;
    }

    /**
     * Starts the main loop of the application
     * Continuously reads user commands, parses them into commands, and executes them.
     * Ends the loop when an exit command is received
     */
    public void run() {
        ui.showWelcomeMessage();

        while (!isExit) {
            getResponse(this.ui.getInput());
        }
    }

    private void exit() throws SageException{
        assert this.isExit;
        try {
            this.ui.closeScanner();
            this.storage.saveTasks(tasks.getAllTasks());
            this.isExit = true;
        } catch (SageException e) {
            this.ui.showErrorMessage(e);
        }
    }

    private String markCommand(String input) throws SageException {
        Task task = getTaskByIndex(input);
        task.markAsDone();
        return this.ui.showMarkedTask(task);
    }

    private String unmarkCommand(String input) throws SageException {
        Task task = getTaskByIndex(input);
        task.markAsNotDone();
        return this.ui.showUnmarkedTask(task);
    }

    private String deleteCommand(String input) throws SageException {
        int index = parseTaskIndex(input);
        Task task = tasks.get(index);
        String response = this.ui.showDeletedTask(task, tasks.size() - 1);
        tasks.removeTask(index);
        return response;
    }

    private String findCommand(String input) throws SageException {
        if (tasks.size() == 0) {
            throw new SageException(EMPTY_TASK_LIST);
        }
        return this.ui.showSearchedTask(this.tasks.searchTasks(input));
    }

    private String toDoCommand(String input) throws SageException {
        try {
            Task todo = new ToDo(input);
            tasks.addTask(todo);
            return this.ui.showAddedTask(todo, tasks.size());
        } catch (StringIndexOutOfBoundsException e) {
            throw new SageException(INVALID_FORMAT);
        }
    }

    private String deadlineCommand(String input) throws SageException {
        String[] parts = parseCommandWithDate(input, " /by ");
        String description = parts[0].trim();
        LocalDateTime by = parseDate(parts[1]);
        Task deadline = new Deadline(description, by);
        tasks.addTask(deadline);
        return this.ui.showAddedTask(deadline, tasks.size());
    }

    private String eventCommand(String input) throws SageException {
        String[] parts = parseCommandWithDateRange(input, " /from ", " /to ");
        String description = parts[0];
        LocalDateTime from = parseDate(parts[1]);
        LocalDateTime to = parseDate(parts[2]);
        if (to.isBefore(from)) {
            throw new SageException("Input the dates correctly, please!!");
        }
        Task event = new Event(description, from, to);
        tasks.addTask(event);
        return ui.showAddedTask(event, tasks.size());
    }

    private String[] parseCommandWithDate(String input, String delimiter) throws SageException {
        String[] parts = input.split(delimiter);
        if (parts.length < 2) {
            throw new SageException(INVALID_FORMAT);
        }
        return parts;
    }

    private String[] parseCommandWithDateRange(String input, String start, String end) throws SageException {
        String[] parts = input.split(start);
        if (parts.length < 2) {
            throw new SageException(INVALID_FORMAT);
        }
        String[] timeParts = parts[1].split(end);
        if (timeParts.length < 2) {
            throw new SageException(INVALID_FORMAT);
        }
        return new String[]{parts[0], timeParts[0], timeParts[1]};
    }

    private LocalDateTime parseDate(String date) throws SageException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm");
            return LocalDateTime.parse(date, formatter);
        } catch (DateTimeParseException e) {
            throw new SageException(INVALID_DATE);
        }
    }

    private Task createEvent(String[] parts, DateTimeFormatter formatter) throws SageException {
        if (parts.length < 2) {
            throw new SageException("Please input correctly");
        }
        String description = parts[0].trim();
        String[] timeParts = parts[1].split(" /to ");
        if (timeParts.length < 2) {
            throw new SageException("Please input correctly");
        }
        LocalDateTime from = LocalDateTime.parse(timeParts[0], formatter);
        LocalDateTime to = LocalDateTime.parse(timeParts[1], formatter);
        if (description.isEmpty()) {
            throw new SageException("What is the date??");
        }
        if (to.isBefore(from)) {
            throw new SageException("Inout the dates correctly please!! -_-");
        }
        return new Event(description, from, to);
    }

    private Task getTaskByIndex(String input) throws SageException {
        int index = parseTaskIndex(input);
        return tasks.get(index);
    }

    private int parseTaskIndex(String input) throws SageException {
        try {
            int index = Integer.parseInt(input) - 1;
            if (index < 0 || index >= tasks.size() || tasks.size() == 0) {
                throw new SageException(INVALID_TASK_NUMBER);
            }
            return index;
        } catch (NumberFormatException e) {
            throw new SageException("Invalid task number format");
        }
    }

    public static void main(String[] args) {
        new Sage().run();
    }
}
