package sigma;

import sigma.command.CommandType;

import java.util.ArrayList;

import sigma.exception.SigmaException;
import sigma.exception.SigmaInvalidArgException;
import sigma.exception.SigmaInvalidTaskException;
import sigma.exception.SigmaMissingArgException;
import sigma.exception.SigmaNaNException;
import sigma.exception.SigmaUnknownCommandException;
import sigma.task.Deadline;
import sigma.task.Event;
import sigma.task.Task;
import sigma.task.Todo;


public class Sigma {
    private Ui ui;
    private boolean isRunning;
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static final String horizontalLine = "____________________________________________________________";

    public Sigma() {
        this.ui = new Ui();
        this.isRunning = true;
    }

    private void run() {
        this.ui.showWelcome();
        while (this.isRunning) {
            handleCommand(this.ui.getInput());
        }
    }

    private void exit() {
        this.ui.closeScanner();
        this.isRunning = false;
    }

    private void handleCommand(String userInput) {
        try {
            switch (Parser.parseCommand(userInput)) {
                case BYE:
                    this.exit();
                    this.ui.showGoodbye();
                    return;
                case LIST:
                    this.ui.showList(tasks);
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
            System.out.println(horizontalLine);
            System.out.println(e);
            System.out.println(horizontalLine);
        }
    }

    private void handleMarkCommand(String userInput) throws SigmaException {
        try {
            int taskNumber = Integer.parseInt(userInput);
            if (taskNumber < 1 || taskNumber > tasks.size()) {
                throw new SigmaInvalidTaskException(taskNumber);
            }
            tasks.get(taskNumber - 1).markAsDone();
            this.ui.showMarkedTask(tasks.get(taskNumber - 1));
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new SigmaInvalidArgException(CommandType.MARK);
        } catch (NumberFormatException e) {
            throw new SigmaNaNException();
        }
    }

    private void handleUnmarkCommand(String userInput) throws SigmaException {
        try {
            int taskNumber = Integer.parseInt(userInput);
            if (taskNumber < 1 || taskNumber > tasks.size()) {
                throw new SigmaInvalidTaskException(taskNumber);
            }
            tasks.get(taskNumber - 1).markAsNotDone();
            this.ui.showUnmarkedTask(tasks.get(taskNumber - 1));
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new SigmaInvalidArgException(CommandType.UNMARK);
        } catch (NumberFormatException e) {
            throw new SigmaNaNException();
        }
    }

    private void handleTodoCommand(String userInput) throws SigmaException {
        try {
            Task todo = new Todo(userInput);
            tasks.add(todo);
            this.ui.showAddedTask(todo, tasks.size());
        } catch (StringIndexOutOfBoundsException e) {
            throw new SigmaMissingArgException(CommandType.TODO);
        }
    }

    private void handleDeadlineCommand(String userInput) throws SigmaException {
        try {
            String[] parts = userInput.split(" /by ");
            if (parts.length < 2) {
                throw new SigmaMissingArgException(CommandType.DEADLINE);
            }
            String description = parts[0].trim();
            String by = parts[1].trim();
            Task deadline = new Deadline(description, by);
            tasks.add(deadline);
            this.ui.showAddedTask(deadline, tasks.size());
        } catch (StringIndexOutOfBoundsException e) {
            throw new SigmaInvalidArgException(CommandType.DEADLINE);
        }
    }

    private void handleEventCommand(String userInput) throws SigmaException {
        try {
            String[] parts = userInput.split(" /from ");
            if (parts.length < 2) {
                throw new SigmaMissingArgException(CommandType.EVENT);
            }
            String description = parts[0].trim();
            String[] timeParts = parts[1].split(" /to ");
            if (timeParts.length < 2) {
                throw new SigmaMissingArgException(CommandType.EVENT);
            }
            String from = timeParts[0].trim();
            String to = timeParts[1].trim();
            if (description.isEmpty()) {
                throw new SigmaMissingArgException(CommandType.EVENT);
            }
            Task event = new Event(description, from, to);
            tasks.add(event);
            this.ui.showAddedTask(event, tasks.size());
        } catch (StringIndexOutOfBoundsException e) {
            throw new SigmaInvalidArgException(CommandType.EVENT);
        }
    }

    private void handleDeleteCommand(String userInput) throws SigmaException {
        try {
            int taskNumber = Integer.parseInt(userInput);
            if (taskNumber < 1 || taskNumber > tasks.size()) {
                throw new SigmaInvalidTaskException(taskNumber);
            }
            this.ui.showDeletedTask(tasks.get(taskNumber - 1), tasks.size() - 1);
            tasks.remove(taskNumber - 1);
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
