package yapper.main;

import java.io.IOException;
import java.util.List;

import yapper.command.CommandType;
import yapper.command.Parser;
import yapper.exception.EmptyDescriptionException;
import yapper.exception.InvalidTaskNumberException;
import yapper.exception.UnknownCommandException;
import yapper.exception.YapperException;
import yapper.storage.Storage;
import yapper.task.Deadline;
import yapper.task.Event;
import yapper.task.Task;
import yapper.task.TaskList;
import yapper.task.Todo;
import yapper.ui.Ui;

    /**
     * The Yapper class serves as the main entry point for the application.
     * It manages user input, processes commands, and interacts with the task list,
     * user interface, and storage components to provide the full functionality
     * of the Yapper task management application.
     */
public class Yapper {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    
    public Yapper(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.printLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the main loop of the Yapper application. This method continuously
     * reads commands from the user, processes them, and interacts with the
     * task list and UI. The loop continues until the user issues the 'bye' command.
     */
    public void run() {
        ui.printWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.printLine();
                CommandType command = Parser.parseCommand(fullCommand);

                switch (command) {
                case LIST:
                    ui.printTasks(tasks);
                    break;
                case MARK:
                    handleMark(fullCommand);
                    break;
                case UNMARK:
                    handleUnmark(fullCommand);
                    break;
                case TODO:
                    handleTodo(fullCommand);
                    break;
                case DEADLINE:
                    handleDeadline(fullCommand);
                    break;
                case EVENT:
                    handleEvent(fullCommand);
                    break;
                case DELETE:
                    handleDelete(fullCommand);
                    break;
                case BYE:
                    isExit = true;
                    ui.printGoodbye();
                    break;
                default:
                    throw new UnknownCommandException();
                }

                storage.save(tasks.getTasks());
            } catch (YapperException | IOException e) {
                ui.printError(e.getMessage());
            } finally {
                ui.printLine();
            }
        }
    }

    private void handleMark(String fullCommand) throws YapperException {
        String[] parts = fullCommand.split(" ");
        if (parts.length < 2) {
            throw new EmptyDescriptionException("mark");
        }
        int taskIndex = Integer.parseInt(parts[1]) - 1;
        if (taskIndex < 0 || taskIndex >= tasks.getSize()) {
            throw new InvalidTaskNumberException(taskIndex);
        }
        Task task = tasks.getTask(taskIndex);
        task.markAsDone();
        ui.printTaskMarked(task);
    }

    private void handleUnmark(String fullCommand) throws YapperException {
        String[] parts = fullCommand.split(" ");
        if (parts.length < 2) {
            throw new EmptyDescriptionException("unmark");
        }
        int taskIndex = Integer.parseInt(parts[1]) - 1;
        if (taskIndex < 0 || taskIndex >= tasks.getSize()) {
            throw new InvalidTaskNumberException(taskIndex);
        }
        Task task = tasks.getTask(taskIndex);
        task.markAsNotDone();
        ui.printTaskUnmarked(task);
    }

    private void handleTodo(String fullCommand) throws YapperException {
        String[] parts = fullCommand.split(" ", 2);
        if (parts.length < 2) {
            throw new EmptyDescriptionException("todo");
        }
        Task task = new Todo(parts[1]);
        tasks.addTask(task);
        ui.printTaskAdded(task, tasks.getSize());
    }

    private void handleDeadline(String fullCommand) throws YapperException {
        String[] parts = fullCommand.split(" /by ");
        if (parts.length < 2) {
            throw new EmptyDescriptionException("deadline");
        }
        String description = parts[0].substring(9).trim();
        Task task = new Deadline(description, parts[1]);
        tasks.addTask(task);
        ui.printTaskAdded(task, tasks.getSize());
    }

    private void handleEvent(String fullCommand) throws YapperException {
        String[] parts = fullCommand.split(" /from ");
        if (parts.length < 2) {
            throw new EmptyDescriptionException("event");
        }
        String[] times = parts[1].split(" /to ");
        if (times.length < 2) {
            throw new YapperException("The event command requires both a start and end time.");
        }
        String description = parts[0].substring(6).trim();
        Task task = new Event(description, times[0], times[1]);
        tasks.addTask(task);
        ui.printTaskAdded(task, tasks.getSize());
    }

    private void handleDelete(String fullCommand) throws YapperException {
        String[] parts = fullCommand.split(" ");
        if (parts.length < 2) {
            throw new EmptyDescriptionException("delete");
        }
        int taskIndex = Integer.parseInt(parts[1]) - 1;
        if (taskIndex < 0 || taskIndex >= tasks.getSize()) {
            throw new InvalidTaskNumberException(taskIndex);
        }
        Task task = tasks.getTask(taskIndex);
        tasks.deleteTask(taskIndex);
        ui.printTaskRemoved(task, tasks.getSize());
    }

    private void handleFind(String fullCommand) throws YapperException {
        String[] parts = fullCommand.split(" ", 2);
        if (parts.length < 2) {
            throw new EmptyDescriptionException("find");
        }
        String keyword = parts[1];
        List<Task> matchingTasks = tasks.findTasks(keyword);
        
        if (matchingTasks.isEmpty()) {
            ui.showNoMatchingTasksMessage();
        } else {
            ui.showMatchingTasks(matchingTasks);
        }
    }
    public static void main(String[] args) {
        new Yapper("data/tasks.txt").run();
    }
}
