package bobby;

import bobby.command.Command;
import bobby.exceptions.BobbyException;
import bobby.parser.Parser;
import bobby.storage.Storage;
import bobby.tasklist.TaskList;
import bobby.tasks.Task;
import bobby.ui.Ui;

import java.util.ArrayList;

public class Bobby {

    private static final String FILE_PATH = "./src/main/data/Bobby.txt";
    private final Ui ui;
    private final Storage storage;
    private final Parser parser;
    private TaskList tasks;


    public Bobby() {
        this.ui = new Ui();
        this.storage = new Storage(FILE_PATH);
        this.parser = new Parser();
        this.tasks = new TaskList();
        tasks = storage.loadTasks();
    }
    public void run() {
        ui.showGreeting();
        boolean isRunning = true;
        while (isRunning) {
            String userInput = ui.readCommand();
            Command command = parser.parseCommand(userInput);
            try {
                switch (command) {
                    case BYE:
                        ui.showExitMessage();
                        isRunning = false;
                        break;
                    case LIST:
                        ui.showTasks(tasks);
                        break;
                    case MARK:
                        int index = Integer.parseInt(userInput.split(" ")[1]) - 1;
                        Task task = tasks.get(index);
                        task.markTask();
                        ui.showTaskMarked(task);
                        storage.saveTasks(tasks);
                        break;
                    case UNMARK:
                        int i = Integer.parseInt(userInput.split(" ")[1]) - 1;
                        Task taskToUnmark = tasks.get(i);
                        taskToUnmark.unmarkTask();
                        ui.showTaskUnmarked(taskToUnmark);
                        storage.saveTasks(tasks);
                        break;
                    case DELETE:
                        int deleteIndex = Integer.parseInt(userInput.split(" ")[1]) - 1;
                        Task deletedTask = tasks.remove(deleteIndex);
                        ui.showTaskDeleted(deletedTask, tasks.size());
                        storage.saveTasks(tasks);
                        break;
                    case SEARCHDATE:
                    case FIND:
                        ArrayList<Task> foundTasks = parser.parseFindCommand(userInput, tasks);
                        ui.showFoundTasks(foundTasks);
                        break;
                    default:
                        Task newTask = parser.parseTask(userInput);
                        tasks.add(newTask);
                        ui.showTaskAdded(newTask, tasks.size());
                        storage.saveTasks(tasks);
                        break;
                }
            } catch (BobbyException e) {
                ui.showError(e.getMessage());
            }
        }

    }

    public static void main(String[] args) {
        new Bobby().run();
    }

}
