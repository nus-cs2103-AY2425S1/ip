package rizz.source;
import java.io.IOException;
import rizz.command.Command;

/**
 * The main class for the Rizz application, which manages tasks.
 * The Rizz class handles the interaction between the user, tasks, and storage.
 */
public class Rizz {
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    /**
     * Constructs a new Rizz application with the specified file path for task storage.
     * Initializes the storage, task list, and user interface.
     *
     * @param filePath The file path where tasks will be saved and loaded from.
     * @throws IOException If an I/O error occurs when loading tasks from the file.
     */
    public Rizz(String filePath) throws IOException {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.tasks = new TaskList(storage.loadTasks());
    }

    /**
     * Runs the main loop of the Rizz application.
     * Greets the user, processes user commands, executes them, and saves tasks after each command.
     *
     * @throws IOException If an I/O error occurs when saving tasks to the file.
     */
    public void run() throws IOException {
        ui.greet();
        boolean isExit = false;
        while (!isExit) {
            String userInput = this.ui.readCommand();
            Command command = Parser.parseCommand(userInput);
            if (command == null) {
                ui.showError("Invalid command. Please try again.");
                continue;
            }
            command.execute(tasks, ui, storage);
            storage.saveTasks(tasks);
        }
    }

    /**
     * The main method that starts the Rizz application.
     * Loads tasks from the specified file and begins processing user input.
     *
     * @param args Command line arguments (not used).
     * @throws IOException If an I/O error occurs when loading tasks from the file.
     */
    public static void main(String[] args) throws IOException {
        new Rizz("./data/rizz.txt").run();
    }

}