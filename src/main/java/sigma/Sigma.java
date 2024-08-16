package sigma;

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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents the main class for the Sigma application.
 * <p>
 * Sigma is a task management application that supports different types of tasks such as
 * to-dos, deadlines, and events. Users can add, delete, mark as done, or list tasks using
 * a command-line interface.
 * </p>
 * <p>
 * The application uses a {@link Storage} class to save and load tasks from a file, and
 * a {@link TaskList} to manage the list of tasks in memory. User interactions are handled
 * by the {@link Ui} class.
 * </p>
 */
public class Sigma {
    private static final String FILEPATH = "./data/sigma.txt";
    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private boolean isRunning;

    /**
     * Constructs a new Sigma application instance.
     * Initializes storage, loads tasks from the file, and sets up the user interface.
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

<<<<<<< HEAD
    public static void main(String[] args) {
        new Sigma().run();
    }

=======
    /**
     * Starts the main loop of the Sigma application.
     * Continuously reads and processes user commands until the user exits the application.
     */
>>>>>>> master
    private void run() {
        this.ui.showWelcome();
        while (this.isRunning) {
            handleCommand(this.ui.getInput());
        }
    }

    /**
     * Handles the exit command.
     * Saves all tasks to the file and closes the application.
     *
     * @throws SigmaFileException if there is an error saving the tasks to the file
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

    /**
     * Processes a user command.
     * <p>
     * Parses the command and executes the corresponding action. If the command is not recognized,
     * a {@link SigmaUnknownCommandException} is thrown.
     * </p>
     *
     * @param userInput the input command from the user
     */
    private void handleCommand(String userInput) {
        try {
            switch (Parser.parseCommand(userInput)) {
            case BYE:
                this.exit();
                this.ui.showGoodbye();
                return;
            case LIST:
                this.ui.showList(taskList);
                break;
            case MARK:
                handleMarkCommand(Parser.parseArgs(CommandType.MARK, userInput));
                break;
            case UNMARK:
                handleUnmarkCommand(Parser.parseArgs(CommandType.UNMARK, userInput));
                break;
            case TODO:
                handleTodoCommand(Parser.parseArgs(CommandType.TODO, userInput));
                break;
            case DEADLINE:
                handleDeadlineCommand(Parser.parseArgs(CommandType.DEADLINE, userInput));
                break;
            case EVENT:
                handleEventCommand(Parser.parseArgs(CommandType.EVENT, userInput));
                break;
            case DELETE:
                handleDeleteCommand(Parser.parseArgs(CommandType.DELETE, userInput));
                break;
            case FIND:
                handleFindCommand(Parser.parseArgs(CommandType.FIND, userInput));
                break;
            default:
                throw new SigmaUnknownCommandException(userInput);
            }
        } catch (SigmaException e) {
            this.ui.showErrorMessage(e);
        }
    }

    /**
     * Handles the mark command, marking a task as done.
     *
     * @param userInput the task number to be marked as done
     * @throws SigmaException if the task number is invalid or if the input is not a valid number
     */
    private void handleMarkCommand(String userInput) throws SigmaException {
        try {
            int taskNumber = Integer.parseInt(userInput);
            if (taskNumber < 1 || taskNumber > taskList.getSize()) {
                throw new SigmaInvalidTaskException(taskNumber);
            }
            taskList.getTask(taskNumber).markAsDone();
            this.ui.showMarkedTask(taskList.getTask(taskNumber));
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new SigmaInvalidArgException(CommandType.MARK);
        } catch (NumberFormatException e) {
            throw new SigmaNaNException();
        }
    }

    /**
     * Handles the unmark command, marking a task as not done.
     *
     * @param userInput the task number to be marked as not done
     * @throws SigmaException if the task number is invalid or if the input is not a valid number
     */
    private void handleUnmarkCommand(String userInput) throws SigmaException {
        try {
            int taskNumber = Integer.parseInt(userInput);
            if (taskNumber < 1 || taskNumber > taskList.getSize()) {
                throw new SigmaInvalidTaskException(taskNumber);
            }
            taskList.getTask(taskNumber).markAsNotDone();
            this.ui.showUnmarkedTask(taskList.getTask(taskNumber));
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new SigmaInvalidArgException(CommandType.UNMARK);
        } catch (NumberFormatException e) {
            throw new SigmaNaNException();
        }
    }

    /**
     * Handles the todo command, adding a new todo task to the list.
     *
     * @param userInput the description of the todo task
     * @throws SigmaException if the input is missing or invalid
     */
    private void handleTodoCommand(String userInput) throws SigmaException {
        try {
            Task todo = new Todo(userInput);
            taskList.addTask(todo);
            this.ui.showAddedTask(todo, taskList.getSize());
        } catch (StringIndexOutOfBoundsException e) {
            throw new SigmaMissingArgException(CommandType.TODO);
        }
    }

    /**
     * Handles the deadline command, adding a new deadline task to the list.
     *
     * @param userInput the description and due date of the deadline task
     * @throws SigmaException if the input is missing, has an invalid date format, or is otherwise invalid
     */
    private void handleDeadlineCommand(String userInput) throws SigmaException {
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
            this.ui.showAddedTask(deadline, taskList.getSize());
        } catch (StringIndexOutOfBoundsException e) {
            throw new SigmaInvalidArgException(CommandType.DEADLINE);
        } catch (DateTimeParseException e) {
            throw new SigmaInvalidDateException(CommandType.DEADLINE);
        }
    }

    /**
     * Handles the event command, adding a new event task to the list.
     *
     * @param userInput the description, start, and end dates of the event task
     * @throws SigmaException if the input is missing, has an invalid date format,
     * or if the end date is before the start date
     */
    private void handleEventCommand(String userInput) throws SigmaException {
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
            this.ui.showAddedTask(event, taskList.getSize());
        } catch (StringIndexOutOfBoundsException e) {
            throw new SigmaInvalidArgException(CommandType.EVENT);
        } catch (DateTimeParseException e) {
            throw new SigmaInvalidDateException(CommandType.EVENT);
        }
    }

    /**
     * Handles the delete command, removing a task from the list.
     *
     * @param userInput the task number to be deleted
     * @throws SigmaException if the task number is invalid or if the input is not a valid number
     */
    private void handleDeleteCommand(String userInput) throws SigmaException {
        try {
            int taskNumber = Integer.parseInt(userInput);
            if (taskNumber < 1 || taskNumber > taskList.getSize()) {
                throw new SigmaInvalidTaskException(taskNumber);
            }
            this.ui.showDeletedTask(taskList.getTask(taskNumber), taskList.getSize() - 1);
            taskList.deleteTask(taskNumber);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new SigmaInvalidArgException(CommandType.DELETE);
        } catch (NumberFormatException e) {
            throw new SigmaNaNException();
        }
    }

<<<<<<< HEAD
    public void handleFindCommand(String userInput) throws SigmaException {
        if (taskList.getSize() == 0) {
            throw new SigmaEmptyTaskListException();
        }

        if (userInput.trim().split(" ").length > 1) {
            throw new SigmaInvalidArgException(CommandType.FIND);
        }

        this.ui.showSearchedTasks(this.taskList.search(userInput));
=======
    /**
     * The main entry point of the Sigma application.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        new Sigma().run();
>>>>>>> master
    }
}
