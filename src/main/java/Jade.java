import UI.UI;
import storage.Storage;
import task.TaskManager;

/**
 * The main entry point for the Jade application.
 * Initialises the storage, task manager, and user interface, and starts the application.
 */
public class Jade {
    private static final String FILE_PATH = "./data/jade.txt";

    /**
     * Main method to start the Jade application.
     * Initialises the Storage, TaskManager, and UI, and then runs the UI.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        Storage storage = new Storage(FILE_PATH);
        TaskManager taskManager = new TaskManager(storage);
        UI ui = new UI(taskManager);
        ui.run();
    }
}