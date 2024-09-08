package patrick;

import patrick.parser.Parser;
import patrick.storage.Storage;
import patrick.tasklist.TaskList;
import patrick.ui.Ui;

/**
 * The {@code Patrick} class represents the main entry point for the application.
 * It initializes the user interface, manages task storage, and controls the main application flow.
 */
public class Patrick {
    private static final String FILE_PATH = "./data/tasks.txt"; // Path to the storage file
    private static final String MESSAGE_BYE = "BYE";
    private final Ui ui; // User interface for interaction
    private TaskList tasks; // Task list with all the tasks

    /**
     * Constructs a {@code Patrick} instance with a specified file path for storage.
     *
     * @param filePath the path to the file where tasks are stored.
     */
    public Patrick(String filePath) {
        ui = new Ui(); // Initialize the user interface
        Storage storage = new Storage(filePath); // Initialize storage with the provided file path
        try {
            // Load tasks from storage and initialize TaskList
            tasks = new TaskList(storage.load());
        } catch (Parser.PatrickException e) {
            Ui.showErrorMsg(e.toString()); // Display error if parsing fails
            tasks = new TaskList();
        } catch (Storage.StorageOperationException e) {
            Ui.showErrorMsg(e.toString()); // Display error if storage operation fails
        }
    }

    /**
     * Creates an instance of the Patrick application with the default file path.
     * <p>
     * This constructor calls the other constructor with the default file path specified by {@code FILE_PATH}.
     */
    public Patrick() {
        this(FILE_PATH);
    }

    /**
     * The main method to start the application.
     *
     * @param args command-line arguments (not used).
     */
    public static void main(String[] args) {
        new Patrick(FILE_PATH); // Create a Patrick instance and run the application
    }

    /**
     * Processes the user's input and generates an appropriate response.
     *
     * @param input The user's input command.
     * @return The response generated based on the user's input.
     */
    public String getResponse(String input) {
        assert input != null : "input cannot be null";
        String response = new Parser().parseTask(input);
        if (response.equals(MESSAGE_BYE)) {
            System.exit(0);
        }
        return response;
    }
}
