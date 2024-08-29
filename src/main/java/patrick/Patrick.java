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
    private final Ui ui;  // User interface for interaction

    private static final String FILE_PATH = "./data/tasks.txt";  // Path to the storage file

    /**
     * Constructs a {@code Patrick} instance with a specified file path for storage.
     *
     * @param filePath the path to the file where tasks are stored.
     */
    public Patrick(String filePath) {
        ui = new Ui();  // Initialize the user interface
        Storage storage = new Storage(filePath);  // Initialize storage with the provided file path
        TaskList tasks;
        try {
            // Load tasks from storage and initialize TaskList
            tasks = new TaskList(storage.load());
        } catch (Parser.PatrickException e) {
            Ui.showErrorMsg(e.toString());  // Display error if parsing fails
            tasks = new TaskList();  // Initialize an empty TaskList
        } catch (Storage.StorageOperationException e) {
            Ui.showErrorMsg(e.toString());  // Display error if storage operation fails
            tasks = new TaskList();  // Initialize an empty TaskList
        }
    }

    /**
     * The main method to start the application.
     *
     * @param args command-line arguments (not used).
     */
    public static void main(String[] args) {
        new Patrick(FILE_PATH).run();  // Create a Patrick instance and run the application
    }

    /**
     * Starts the application, displays the welcome message, and enters the command loop.
     */
    private void run() {
        ui.welcomeMessage();  // Display the welcome message
        runTillBye();  // Run the command loop until the user types 'bye'
        exit();  // Display the goodbye message and exit
    }

    /**
     * Displays the goodbye message and exits the application.
     */
    private void exit() {
        ui.showGoodbyeMsg();  // Display the goodbye message
        System.exit(0);  // Terminate the application
    }

    /**
     * Enters the command loop, parsing and executing user commands until 'bye' is entered.
     */
    private void runTillBye() {
        Parser.Type input;
        do {
            String userInputMsg = ui.getUserCommand();  // Get user input
            input = new Parser().parseTask(userInputMsg);  // Parse and execute the command
        } while (!input.equals(Parser.Type.BYE));  // Continue until 'bye' is entered
    }
}
