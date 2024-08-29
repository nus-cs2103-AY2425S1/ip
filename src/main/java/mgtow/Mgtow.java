package mgtow;

import mgtow.storage.Storage;
import mgtow.task.Task;
import mgtow.task.TaskList;
import mgtow.ui.Ui;
import mgtow.util.InvalidTaskException;
import mgtow.util.Parser;
import java.time.LocalDate;

public class Mgtow {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

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
                    case "find":
                        LocalDate date = Parser.parseDate(commandParts[1]);
                        ui.showTasksOnDate(tasks.getTasksOnDate(date), date);
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
