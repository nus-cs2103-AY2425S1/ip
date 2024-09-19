package mgtow;

import mgtow.storage.Storage;
import mgtow.task.Task;
import mgtow.task.TaskList;
import mgtow.ui.Ui;
import mgtow.util.InvalidTaskException;
import mgtow.util.Parser;

import java.util.ArrayList;

/**
 * Main class for the Mgtow (Man Going Their Own Way) task management application.
 * This class initializes the application, handles user interactions, and manages tasks.
 */
public class Mgtow {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a new Mgtow application instance.
     *
     * @param filePath The file path for storing tasks.
     */
    public Mgtow(String filePath) {
        ui = new Ui();
        assert ui != null : "UI should not be null";
        storage = new Storage(filePath);
        assert storage != null : "Storage should not be null";
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (InvalidTaskException e) {
            ui.showLoadingError();
            tasks = new TaskList();
            assert tasks != null : "TaskList should not be null";
        }
    }

    /**
     * Processes a user command and returns the appropriate response.
     *
     * @param fullCommand The full command string entered by the user.
     * @return A string response to be displayed to the user.
     */
    public String processCommand(String fullCommand) {
        try {
            String[] commandParts = Parser.parseCommand(fullCommand);
            switch (commandParts[0]) {
                case "bye":
                    return ui.getGoodbyeMessage();
                case "list":
                    return ui.getTaskListString(tasks.getTasks());
                case "mark":
                    tasks.markTask(Integer.parseInt(commandParts[1]) - 1);
                    storage.saveTasks(tasks.getTasks());
                    return ui.getMarkTaskMessage(tasks.getTasks().get(Integer.parseInt(commandParts[1]) - 1));
                case "unmark":
                    tasks.unmarkTask(Integer.parseInt(commandParts[1]) - 1);
                    storage.saveTasks(tasks.getTasks());
                    return ui.getUnmarkTaskMessage(tasks.getTasks().get(Integer.parseInt(commandParts[1]) - 1));
                case "delete":
                    Task deletedTask = tasks.deleteTask(Integer.parseInt(commandParts[1]) - 1);
                    storage.saveTasks(tasks.getTasks());
                    return ui.getDeleteTaskMessage(deletedTask, tasks.getTasks().size());
                case "todo":
                case "deadline":
                case "event":
                    Task newTask = Parser.createTask(commandParts);
                    tasks.addTask(newTask);
                    storage.saveTasks(tasks.getTasks());
                    return ui.getAddTaskMessage(newTask, tasks.getTasks().size());
                case "find":
                    ArrayList<Task> foundTasks = tasks.findTasks(commandParts[1]);
                    return ui.getFoundTasksMessage(foundTasks, commandParts[1]);
                case "sort":
                    tasks.sortTasks();
                    return ui.getSortedTaskListString(tasks.getTasks());
                default:
                    throw new InvalidTaskException("What you talking?");
            }
        } catch (InvalidTaskException e) {
            return ui.getErrorMessage(e.getMessage());
        } catch (NumberFormatException e) {
            return ui.getErrorMessage("Invalid number format. Please enter a valid number.");
        }
    }

    /**
     * Runs the Mgtow application in command-line mode.
     * This method handles the main interaction loop with the user.
     */
    public void run() {
        ui.showWelcome();
        boolean isFinished = false;
        while (!isFinished) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                String response = processCommand(fullCommand);
                ui.showMessage(response);
                if (fullCommand.equals("bye")) {
                    isFinished = true;
                }
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Gets the UI instance used by this Mgtow application.
     *
     * @return The Ui instance.
     */
    public Ui getUi() {
        return ui;
    }

    /**
     * The main entry point for the Mgtow application when run in command-line mode.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        new Mgtow("./data/MGTOW.txt").run();
    }
}
