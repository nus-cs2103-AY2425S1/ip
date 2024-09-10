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
    public String executeCommand(String fullCommand) {
        try {
            Command c = Parser.parse(fullCommand);
            assert storage != null : "Storage cannot be null.";
            assert tasks != null : "TaskList cannot be null.";
            assert ui != null : "Ui cannot be null.";
            return c.execute(this.storage, this.tasks, this.ui);
        } catch (DownyException e) {
            return Ui.showErrorMessage(e.getMessage());
        }
    }

}
