package orion;

import java.util.List;

import orion.commands.Command;
import orion.orionExceptions.FileInitializationException;
import orion.orionExceptions.OrionException;
import orion.parser.Parser;
import orion.storage.Storage;
import orion.task.DeadlineDetails;
import orion.task.EventDetails;
import orion.task.Task;
import orion.taskList.TaskList;
import orion.ui.UI;

/**
 * The Orion class is the main entry point for the task management application.
 * It initializes the necessary components and handles the main program loop
 * that processes user input commands.
 */
public class Orion {
    private static TaskList manager;
    private static UI ui = new UI();

    static {
        try {
            Storage storage = new Storage();
            manager = new TaskList(storage);
        } catch (FileInitializationException e) {
            ui.showError("Failed to initialize TaskList: " + e.getMessage());
            System.exit(1);
        }
    }

    public static Parser parser = new Parser();

    /**
     * The main method that starts the application and handles the main loop
     * for processing user commands.
     *
     * @param args Command-line arguments passed to the program (not used).
     */
    public static void main(String[] args) {
        ui.showWelcome();

        while (true) {
            String input = ui.readUserInput();
            String[] parts = input.split(" ", 2);
            Command command = Command.fromString(parts[0]);

            switch (command) {
                case BYE:
                    ui.showGoodbye();
                    return;
                case LIST:
                    handleList(parts);
                    break;
                case MARK:
                    handleMark(parts);
                    break;
                case UNMARK:
                    handleUnmark(parts);
                    break;
                case TODO:
                    handleTodo(parts);
                    break;
                case EVENT:
                    handleEvent(parts);
                    break;
                case DEADLINE:
                    handleDeadline(parts);
                    break;
                case DELETE:
                    handleDelete(parts);
                    break;
                case FIND:
                    handleFind(parts);
                    break;
                case UNKNOWN:
                default:
                    ui.showError("Unknown command: " + parts[0]);
            }
        }
    }

    /**
     * Handles the 'list' command by validating the input and displaying the current
     * list of tasks.
     *
     * @param parts The split user input containing the command and its arguments.
     */
    private static void handleList(String[] parts) {
        try {
            parser.validateListCommand(parts);
            List<Task> taskList = manager.loadTasksFromFile();
            ui.showTaskList(taskList);
        } catch (OrionException e) {
            ui.showError(e.getMessage());
        }
    }

    /**
     * Handles the 'event' command by validating the input, creating an event task,
     * and adding it to the task list.
     *
     * @param parts The split user input containing the command and its arguments.
     */
    private static void handleEvent(String[] parts) {
        try {
            EventDetails details = parser.validateEventCommand(parts);
            Task temp = manager.addEvent(details);
            ui.showTaskAdded(temp, manager.getSize());
        } catch (OrionException e) {
            ui.showError(e.getMessage());
        }
    }

    /**
     * Handles the 'deadline' command by validating the input, creating a deadline
     * task, and adding it to the task list.
     *
     * @param parts The split user input containing the command and its arguments.
     */
    private static void handleDeadline(String[] parts) {
        try {
            DeadlineDetails deadlineDetails = parser.validateDeadlineCommand(parts);
            Task newDeadline = manager.addDeadline(deadlineDetails);
            ui.showTaskAdded(newDeadline, manager.getSize());
        } catch (OrionException e) {
            ui.showError(e.getMessage());
        }
    }

    /**
     * Handles the 'todo' command by validating the input, creating a todo task, and
     * adding it to the task list.
     *
     * @param parts The split user input containing the command and its arguments.
     */
    private static void handleTodo(String[] parts) {
        try {
            String description = parser.validateTodoCommand(parts);
            Task temp = manager.addTodo(description);
            ui.showTaskAdded(temp, manager.getSize());
        } catch (OrionException e) {
            ui.showError(e.getMessage());
        }
    }

    /**
     * Handles the 'mark' command by validating the input and marking the specified
     * task as done.
     *
     * @param parts The split user input containing the command and its arguments.
     */
    private static void handleMark(String[] parts) {
        try {
            int index = parser.validateMarkAndUnMarkCommand(parts, manager);
            Task temp = manager.markAsDone(index);
            ui.showTaskMarked(temp);
        } catch (OrionException e) {
            ui.showError(e.getMessage());
        }
    }

    /**
     * Handles the 'unmark' command by validating the input and marking the
     * specified task as not done.
     *
     * @param parts The split user input containing the command and its arguments.
     */
    private static void handleUnmark(String[] parts) {
        try {
            int index = parser.validateMarkAndUnMarkCommand(parts, manager);
            Task temp = manager.unmarkAsDone(index);
            ui.showTaskUnmarked(temp);
        } catch (OrionException e) {
            ui.showError(e.getMessage());
        }
    }

    /**
     * Handles the 'delete' command by validating the input and deleting the
     * specified task from the task list.
     *
     * @param parts The split user input containing the command and its arguments.
     */
    private static void handleDelete(String[] parts) {
        try {
            int index = parser.validateDeleteCommand(parts, manager);
            Task deletedTask = manager.deleteTask(index);
            ui.showTaskDeleted(deletedTask, manager.getSize());
        } catch (OrionException e) {
            ui.showError(e.getMessage());
        }
    }

    private static void handleFind(String[] parts) {
        try {
            String keyword = parser.validateFindCommand(parts);
            List<Task> matchingTasks = manager.findTasks(keyword);
            ui.showTaskList(matchingTasks, true);
        } catch (OrionException e) {
            ui.showError(e.getMessage());
        }
    }

}
