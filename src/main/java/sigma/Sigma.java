package sigma;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import sigma.command.CommandType;
import sigma.exception.SigmaEmptyTaskListException;
import sigma.exception.SigmaException;
import sigma.exception.SigmaFileException;
import sigma.exception.SigmaInvalidArgException;
import sigma.exception.SigmaInvalidDateException;
import sigma.exception.SigmaInvalidDateRangeException;
import sigma.exception.SigmaInvalidTaskException;
import sigma.exception.SigmaMissingArgException;
import sigma.exception.SigmaNaNException;
import sigma.exception.SigmaUnknownCommandException;
import sigma.task.Deadline;
import sigma.task.Event;
import sigma.task.Task;
import sigma.task.TaskList;
import sigma.task.Todo;

/**
 * The {@code Sigma} class serves as the main application class, handling
 * the initialization, command processing, and execution of the task management
 * application. It interacts with the user through the {@code Ui} class,
 * manages tasks using the {@code TaskList} class, and handles file storage
 * through the {@code Storage} class.
 */
public class Sigma {
    private static final String FILEPATH = "./data/sigma.txt";
    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private boolean isRunning;
    private CommandType commandType;

    /**
     * Constructs a {@code Sigma} object and initializes the required components.
     * It attempts to load the task list from the specified file.
     */
    public Sigma() {
        this.storage = new Storage(FILEPATH);
        this.ui = new Ui();
        this.isRunning = true;
        try {
            this.taskList = new TaskList(this.storage.load());
        } catch (SigmaException e) {
            this.ui.showErrorMessage(e);
        }
    }

    /**
     * Starts the application by displaying the welcome message and entering
     * the command-processing loop.
     */
    private void run() {
        System.out.println(this.ui.showWelcome());
        while (this.isRunning) {
            getResponse(this.ui.getInput());
        }
    }

    /**
     * Exits the application by saving the task list to a file, closing the
     * scanner, and setting the running flag to false.
     *
     * @throws SigmaFileException If there is an error saving the task list.
     */
    private void exit() throws SigmaFileException {
        try {
            this.ui.closeScanner();
            this.storage.save(taskList.getAllTasks());
            this.isRunning = false;
        } catch (SigmaFileException e) {
            this.ui.showErrorMessage(e);
        }
    }

    public String getWelcome() {
        return this.ui.showWelcome();
    }

    /**
     * Handles the user's input by parsing and executing the corresponding command.
     * If an error occurs during command execution, an appropriate error message
     * is displayed.
     *
     * @param userInput The user's input command.
     */
    public String getResponse(String userInput) {
        try {
            commandType = Parser.parseCommand(userInput);
            switch (commandType) {
            case BYE:
                this.exit();
                return this.ui.showGoodbye();
            case LIST:
                return this.ui.showList(taskList);
            case MARK:
                return handleMarkCommand(Parser.parseArgs(CommandType.MARK, userInput));
            case UNMARK:
                return handleUnmarkCommand(Parser.parseArgs(CommandType.UNMARK, userInput));
            case TODO:
                return handleTodoCommand(Parser.parseArgs(CommandType.TODO, userInput));
            case DEADLINE:
                return handleDeadlineCommand(Parser.parseArgs(CommandType.DEADLINE, userInput));
            case EVENT:
                return handleEventCommand(Parser.parseArgs(CommandType.EVENT, userInput));
            case DELETE:
                return handleDeleteCommand(Parser.parseArgs(CommandType.DELETE, userInput));
            case FIND:
                return handleFindCommand(Parser.parseArgs(CommandType.FIND, userInput));
            default:
                throw new SigmaUnknownCommandException(userInput);
            }
        } catch (SigmaException e) {
            return this.ui.showErrorMessage(e);
        }
    }

    /**
     * Handles the "mark" command, marking a task as done.
     *
     * @param userInput The task number to mark as done.
     * @throws SigmaException If the task number is invalid.
     */
    private String handleMarkCommand(String userInput) throws SigmaException {
        try {
            int taskNumber = Integer.parseInt(userInput);
            if (taskNumber < 1 || taskNumber > taskList.getSize()) {
                throw new SigmaInvalidTaskException(taskNumber);
            }
            taskList.getTask(taskNumber).markAsDone();
            return this.ui.showMarkedTask(taskList.getTask(taskNumber));
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new SigmaInvalidArgException(CommandType.MARK);
        } catch (NumberFormatException e) {
            throw new SigmaNaNException();
        }
    }

    /**
     * Handles the "unmark" command, marking a task as not done.
     *
     * @param userInput The task number to mark as not done.
     * @throws SigmaException If the task number is invalid.
     */
    private String handleUnmarkCommand(String userInput) throws SigmaException {
        try {
            int taskNumber = Integer.parseInt(userInput);
            if (taskNumber < 1 || taskNumber > taskList.getSize()) {
                throw new SigmaInvalidTaskException(taskNumber);
            }
            taskList.getTask(taskNumber).markAsNotDone();
            return this.ui.showUnmarkedTask(taskList.getTask(taskNumber));
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new SigmaInvalidArgException(CommandType.UNMARK);
        } catch (NumberFormatException e) {
            throw new SigmaNaNException();
        }
    }

    /**
     * Handles the "todo" command, adding a new to-do task to the list.
     *
     * @param userInput The description of the to-do task.
     * @throws SigmaException If the description is missing or invalid.
     */
    private String handleTodoCommand(String userInput) throws SigmaException {
        try {
            Task todo = new Todo(userInput);
            taskList.addTask(todo);
            return this.ui.showAddedTask(todo, taskList.getSize());
        } catch (StringIndexOutOfBoundsException e) {
            throw new SigmaMissingArgException(CommandType.TODO);
        }
    }

    /**
     * Handles the "deadline" command, adding a new deadline task to the list.
     *
     * @param userInput The description and due date of the deadline task.
     * @throws SigmaException If the description or due date is missing or invalid.
     */
    private String handleDeadlineCommand(String userInput) throws SigmaException {
        try {
            String[] parts = userInput.split(" /by ");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm");
            if (parts.length < 2) {
                throw new SigmaMissingArgException(CommandType.DEADLINE);
            }
            String description = parts[0].trim();
            LocalDateTime by = LocalDateTime.parse(parts[1], formatter);
            Task deadline = new Deadline(description, by);
            taskList.addTask(deadline);
            return this.ui.showAddedTask(deadline, taskList.getSize());
        } catch (StringIndexOutOfBoundsException e) {
            throw new SigmaInvalidArgException(CommandType.DEADLINE);
        } catch (DateTimeParseException e) {
            throw new SigmaInvalidDateException(CommandType.DEADLINE);
        }
    }

    /**
     * Handles the "event" command, adding a new event task to the list.
     *
     * @param userInput The description, start time, and end time of the event task.
     * @throws SigmaException If the description, start time, or end time is missing or invalid.
     */
    private String handleEventCommand(String userInput) throws SigmaException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm");
            String[] parts = userInput.split(" /from ");
            if (parts.length < 2) {
                throw new SigmaMissingArgException(CommandType.EVENT);
            }
            String description = parts[0].trim();
            String[] timeParts = parts[1].split(" /to ");
            if (timeParts.length < 2) {
                throw new SigmaMissingArgException(CommandType.EVENT);
            }
            LocalDateTime from = LocalDateTime.parse(timeParts[0], formatter);
            LocalDateTime to = LocalDateTime.parse(timeParts[1], formatter);
            if (description.isEmpty()) {
                throw new SigmaMissingArgException(CommandType.EVENT);
            }
            if (to.isBefore(from)) {
                throw new SigmaInvalidDateRangeException();
            }
            Task event = new Event(description, from, to);
            taskList.addTask(event);
            return this.ui.showAddedTask(event, taskList.getSize());
        } catch (StringIndexOutOfBoundsException e) {
            throw new SigmaInvalidArgException(CommandType.EVENT);
        } catch (DateTimeParseException e) {
            throw new SigmaInvalidDateException(CommandType.EVENT);
        }
    }

    /**
     * Handles the "delete" command, removing a task from the list.
     *
     * @param userInput The task number to delete.
     * @throws SigmaException If the task number is invalid.
     */
    private String handleDeleteCommand(String userInput) throws SigmaException {
        try {
            int taskNumber = Integer.parseInt(userInput);
            if (taskNumber < 1 || taskNumber > taskList.getSize()) {
                throw new SigmaInvalidTaskException(taskNumber);
            }
            String s = this.ui.showDeletedTask(taskList.getTask(taskNumber), taskList.getSize() - 1);
            taskList.deleteTask(taskNumber);
            return s;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new SigmaInvalidArgException(CommandType.DELETE);
        } catch (NumberFormatException e) {
            throw new SigmaNaNException();
        }
    }

    /**
     * Handles the "find" command, searching for tasks containing the keyword.
     *
     * @param userInput The keyword to search for in tasks.
     * @throws SigmaException If the keyword is missing or invalid.
     */
    public String handleFindCommand(String userInput) throws SigmaException {
        if (taskList.getSize() == 0) {
            throw new SigmaEmptyTaskListException();
        }

        if (userInput.trim().split(" ").length > 1) {
            throw new SigmaInvalidArgException(CommandType.FIND);
        }

        return this.ui.showSearchedTasks(this.taskList.search(userInput));
    }

    public CommandType getCommandType() {
        return commandType;
    }

    /**
     * The main method serves as the entry point to the application.
     * It creates a new instance of {@code Sigma} and runs the application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new Sigma().run();
    }
}
