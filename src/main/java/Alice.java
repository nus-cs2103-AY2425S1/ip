import exceptions.AliceException;
import storage.Storage;
import storage.TaskList;
import ui.Ui;

/**
 * The entry point to the application.
 * Encapsulates a TaskList for storing tasks, a Storage for storing and loading
 * input data, and a Ui for handling user interaction.
 */
public class Alice {
    private final TaskList tasks;
    private final Storage storage;
    private final Ui ui;

    public Alice(String filePath) {
        storage = new Storage(filePath);
        // load tasks
        storage.loadTasks();
        tasks = storage.getTasks();

        // initialise UI with the loaded tasks
        ui = new Ui(tasks);
    }
    
    public static void main(String[] args) {
        // create instance of Alice with loaded tasks
        new Alice("data.txt").run();
    }

    /**
     * Starts the program.
     */
    public void run() {
        ui.showWelcome();
        ui.getInput();
        ui.exitMessage();
        storage.saveTasks(tasks);
    }
}
