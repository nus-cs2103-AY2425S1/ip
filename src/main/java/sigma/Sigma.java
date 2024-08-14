package sigma;

import sigma.command.CommandType;
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


public class Sigma {
    private static final String FILEPATH = "./data/sigma.txt";
    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private boolean isRunning;

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

    private void run() {
        this.ui.showWelcome();
        while (this.isRunning) {
            handleCommand(this.ui.getInput());
        }
    }

    private void exit() throws SigmaFileException {
        try {
            this.ui.closeScanner();
            this.storage.save(taskList.getAllTasks());
            this.isRunning = false;
        } catch (SigmaFileException e) {
            this.ui.showErrorMessage(e);
        }
    }

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
            default:
                throw new SigmaUnknownCommandException(userInput);
            }
        } catch (SigmaException e) {
            this.ui.showErrorMessage(e);
        }
    }

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

    private void handleTodoCommand(String userInput) throws SigmaException {
        try {
            Task todo = new Todo(userInput);
            taskList.addTask(todo);
            this.ui.showAddedTask(todo, taskList.getSize());
        } catch (StringIndexOutOfBoundsException e) {
            throw new SigmaMissingArgException(CommandType.TODO);
        }
    }

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

    public static void main(String[] args) {
        new Sigma().run();
    }
}
