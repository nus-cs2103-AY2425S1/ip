package echo;

import echo.backend.Storage;
import echo.task.TaskList;
/**
 * The Echo class serves as the main entry point for the Echo application.
 * It initializes the necessary components such as the TaskList, Ui, and Storage,
 * and manages the interaction between them.
 */
public class Echo {
    private TaskList taskList;
    private Ui ui;
    private Storage storage;
    /**
     * Constructs an Echo object with the specified file path for storage.
     *
     * @param filePath The file path where the tasks are stored.
     */
    public Echo(String filePath) {
        this.storage = new Storage(filePath);
        try {
            taskList = storage.load();
        } catch (EchoException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }
        ui = new Ui(taskList);
    }
    /**
     * Runs the Echo application by accepting user input and
     * saving tasks to the file when the user quits the app.
     */
    public void run() {
        ui.acceptInput();
        storage.saveToFile();
    }
    /**
     * The main method that launches the Echo application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new Echo("src/main/data/savedTasks.txt").run();
    }
}
