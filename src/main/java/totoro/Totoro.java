package totoro;

import totoro.command.*;
import totoro.exception.*;
import totoro.storage.Storage;
import totoro.task.*;
import totoro.parser.Parser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

/**
 * The main class for the Totoro task management application
 * This class initializes the application components, manages user interactions,
 * and controls the overall flow of the application
 */
public class Totoro {
    public static final String DIRECTORY_PATH = "data/";
    public static final String FILE_PATH = DIRECTORY_PATH + "/sage.txt";
    private Storage storage;
    private Ui ui;
    private TaskList tasks;
    private boolean isExit;
    private CommandType commandType;

    /**
     * Constructs a Totoro instance with the specified file path
     * Initializes the UI, storage, parser, and task list.
     * Attempts to load tasks from the file; if unsuccessful, initializes with an empty task list
     *
     */
    public Totoro() {
        this.ui = new Ui();
        this.storage = new Storage(FILE_PATH);
        this.isExit = false;
        try {
            this.tasks = new TaskList(this.storage.load());
        } catch (TotoroException e) {
            this.ui.showErrorMessage(e);
        }
    }

    /**
     * Processes user input and returns an appropriate response
     * <p>
     *     Parses the input command, determines the corresponding action,
     *     and returns the result of that action. If the command is unknown,
     *     an exception is thrown
     * </p>
     *
     * @param input The user input command
     * @return A string response based on the executed command
     *
     */
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
                case SCHEDULE:
                    return scheduleCommand(Parser.parseArgs(CommandType.SCHEDULE, input));
                case TODO:
                    return toDoCommand(Parser.parseArgs(CommandType.TODO, input));
                case DEADLINE:
                    return deadlineCommand(Parser.parseArgs(CommandType.DEADLINE, input));
                case EVENT:
                    return eventCommand(Parser.parseArgs(CommandType.EVENT, input));
                case HELP:
                    return this.ui.showHelp();
                default:
                    throw new TotoroUnknownCommandException(input);
            }
        } catch (TotoroException e) {
            return this.ui.showErrorMessage(e);
        }
    }

    /**
     * Retrieves the command type of the last processed command
     *
     * @return The command type of the last processed command
     *
     */
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

    /**
     * Exits the application and saves the current tasks to storage
     *
     * @throws TotoroFileException If there is an error while saving tasks to storage
     *
     */
    private void exit() throws TotoroFileException {
        assert this.isExit;
        try {
            this.ui.closeScanner();
            this.storage.saveTasks(tasks.getAllTasks());
            this.isExit = true;
        } catch (TotoroFileException e) {
            this.ui.showErrorMessage(e);
        }
    }

    private String markCommand(String input) throws TotoroException {
        Task task = getTaskByIndex(input);
        task.markAsDone();
        return this.ui.showMarkedTask(task);
    }

    private String unmarkCommand(String input) throws TotoroException {
        Task task = getTaskByIndex(input);
        task.markAsNotDone();
        return this.ui.showUnmarkedTask(task);
    }

    private String deleteCommand(String input) throws TotoroException {
        int index = parseTaskIndex(input);
        Task task = tasks.get(index);
        String response = this.ui.showDeletedTask(task, tasks.size() - 1);
        tasks.removeTask(index);
        return response;
    }

    private String findCommand(String input) throws TotoroException {
        if (tasks.size() == 0) {
            throw new TotoroEmptyTaskListException();
        }
        return this.ui.showSearchedTask(this.tasks.searchTasks(input));
    }

    private String scheduleCommand(String input) throws TotoroException {
        if (tasks.size() == 0 ) {
            throw new TotoroEmptyTaskListException();
        }
        LocalDate date;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
            date = LocalDate.parse(input.trim(), formatter);
        } catch (DateTimeParseException e) {
            throw new TotoroInvalidDateException(CommandType.SCHEDULE);
        }
        List<Task> tasksForDate = tasks.getTaskForDate(date);
        return this.ui.showScheduledTask(tasksForDate, date);
    }

    private String toDoCommand(String input) throws TotoroException {
        try {
            if (input.trim().isEmpty()) {
                throw new TotoroMissingArgException(CommandType.TODO);
            }
            Task todo = new ToDo(input);
            tasks.addTask(todo);
            return this.ui.showAddedTask(todo, tasks.size());
        } catch (StringIndexOutOfBoundsException e) {
            throw new TotoroInvalidArgException(CommandType.TODO);
        }
    }

    private String deadlineCommand(String input) throws TotoroException {
        String[] parts = parseCommandWithDate(input, " /by ");
        String description = parts[0].trim();
        LocalDateTime by = parseDate(parts[1]);
        Task deadline = new Deadline(description, by);
        tasks.addTask(deadline);
        return this.ui.showAddedTask(deadline, tasks.size());
    }

    private String eventCommand(String input) throws TotoroException {
        String[] parts = parseCommandWithDateRange(input, " /from ", " /to ");
        String description = parts[0];
        LocalDateTime from = parseDate(parts[1]);
        LocalDateTime to = parseDate(parts[2]);
        if (to.isBefore(from)) {
            throw new TotoroInvalidDateRangeException();
        }
        Task event = new Event(description, from, to);
        tasks.addTask(event);
        return ui.showAddedTask(event, tasks.size());
    }

    private String[] parseCommandWithDate(String input, String delimiter) throws TotoroException {
        String[] parts = input.split(delimiter);
        if (parts.length < 2) {
            throw new TotoroMissingArgException(CommandType.DEADLINE);
        }
        return parts;
    }

    private String[] parseCommandWithDateRange(String input, String start, String end) throws TotoroException {
        String[] parts = input.split(start);
        if (parts.length < 2) {
            throw new TotoroMissingArgException(CommandType.EVENT);
        }
        String[] timeParts = parts[1].split(end);
        if (timeParts.length < 2) {
            throw new TotoroMissingArgException(CommandType.EVENT);
        }
        return new String[]{parts[0], timeParts[0], timeParts[1]};
    }

    private LocalDateTime parseDate(String date) throws TotoroException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm");
            return LocalDateTime.parse(date, formatter);
        } catch (DateTimeParseException e) {
            throw new TotoroInvalidDateException(CommandType.DEADLINE);
        }
    }

    private Task getTaskByIndex(String input) throws TotoroException {
        int index = parseTaskIndex(input);
        return tasks.get(index);
    }

    private int parseTaskIndex(String input) throws TotoroException {
        try {
            int index = Integer.parseInt(input) - 1;
            if (index < 0 || index >= tasks.size() || tasks.size() == 0) {
                throw new TotoroInvalidTaskNumberException(index);
            }
            return index;
        } catch (NumberFormatException e) {
            throw new TotoroInvalidTaskNumberException(-1);
        }
    }

    /**
     * The main method to start the application
     *
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        new Totoro().run();
    }
}
