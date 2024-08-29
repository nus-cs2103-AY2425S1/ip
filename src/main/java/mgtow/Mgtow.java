package mgtow;

import mgtow.storage.Storage;
import mgtow.task.Task;
import mgtow.task.TaskList;
import mgtow.ui.Ui;
import mgtow.util.InvalidTaskException;
import mgtow.util.Parser;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Main class for the MGTOW (Man Going Their Own Way) task management application.
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
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (InvalidTaskException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the Mgtow application, handling user input and executing commands.
     */
    public void run() {
        ui.showWelcome();
        boolean isFinished = false;
        while (!isFinished) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                String[] commandParts = Parser.parseCommand(fullCommand);
                switch (commandParts[0]) {
                    case "bye":
                        isFinished = true;
                        break;
                    case "list":
                        tasks.listAllTasks();
                        break;
                    case "mark":
                        tasks.markTask(Integer.parseInt(commandParts[1]) - 1);
                        storage.saveTasks(tasks.getTasks());
                        break;
                    case "unmark":
                        tasks.unmarkTask(Integer.parseInt(commandParts[1]) - 1);
                        storage.saveTasks(tasks.getTasks());
                        break;
                    case "delete":
                        tasks.deleteTask(Integer.parseInt(commandParts[1]) - 1);
                        storage.saveTasks(tasks.getTasks());
                        break;
                    case "todo":
                    case "deadline":
                    case "event":
                        Task newTask = Parser.createTask(commandParts);
                        tasks.addTask(newTask);
                        storage.saveTasks(tasks.getTasks());
                        break;
                    case "date":
                        LocalDate date = Parser.parseDate(commandParts[1]);
                        ui.showTasksOnDate(tasks.getTasksOnDate(date), date);
                        break;
                    case "find":
                        if (commandParts.length < 2 || commandParts[1].trim().isEmpty()) {
                            throw new InvalidTaskException("Please provide a keyword to search for.");
                        }
                        ArrayList<Task> foundTasks = tasks.findTasks(commandParts[1]);
                        ui.showFoundTasks(foundTasks, commandParts[1]);
                        break;
                    default:
                        throw new InvalidTaskException("What you talking?");
                }
            } catch (InvalidTaskException e) {
                ui.showError(e.getMessage());
            } catch (NumberFormatException e) {
                ui.showError("Invalid number format. Please enter a valid number.");
            } finally {
                ui.showLine();
            }
        }
        ui.showGoodbye();
    }

    public static void main(String[] args) {
        new Mgtow("./data/mgtow.MGTOW.txt").run();
    }
}
