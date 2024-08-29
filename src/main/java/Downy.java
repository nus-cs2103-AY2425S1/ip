import commands.Command;
import exceptions.DownyException;
import parser.Parser;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;

/**
 * The {@code Downy} class is the main class for the task management application.
 * It initializes the necessary components, such as storage, task list, and user interface,
 * and manages the application's run loop, processing user commands until the user exits the application.
 */
public class Downy {

    /** The storage handler for saving and loading tasks. */
    private Storage storage;

    /** The list of tasks currently managed by the application. */
    private TaskList tasks;

    /** The user interface handler for interacting with the user. */
    private Ui ui;

    /**
     * Constructs a new {@code Downy} application instance with the specified file path for task storage.
     *
     * @param filePath The file path where tasks are stored.
     */
    public Downy(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.tasks = new TaskList();
        this.tasks.loadTasks(storage);
    }

    /**
     * Runs the application, displaying the welcome message, processing user commands,
     * and continuing until the user issues an exit command.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(this.storage, this.tasks, this.ui);
                isExit = c.isExit();
            } catch (DownyException e) {
                ui.showErrorMessage(e.getMessage());
            }
        }
        ui.showExitMessage();
    }

    /**
     * The main method of the application. Initializes a new {@code Downy} instance
     * and starts the application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new Downy("data/tasks.txt").run();
    }
}
