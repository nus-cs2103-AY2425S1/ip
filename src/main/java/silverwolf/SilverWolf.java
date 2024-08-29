package silverwolf;

import silverwolf.command.Command;
import silverwolf.exception.SilverWolfException;
import silverwolf.parser.Parser;
import silverwolf.storage.Storage;
import silverwolf.task.TaskList;
import silverwolf.ui.Ui;

/**
 * The main class for the SilverWolf chatbot application.
 * It initializes the necessary components and runs the chatbot loop.
 */
public class SilverWolf {

    // constants for directory and file paths
    public static final String DIRECTORY_PATH = "data/";
    public static final String FILE_PATH = DIRECTORY_PATH + "silverWolf.txt";
    private Storage storage; // Handles the saving and loading of tasks to and from a file
    private TaskList tasks; // Stores the list of tasks
    private Ui ui; // Manages user interaction

    /**
     * Constructs a SilverWolfBeta object and initializes the UI, storage, and task list.
     *
     * @param filePath The file path where tasks will be saved and loaded from.
     */
    public SilverWolf(String filePath) {
        this.ui = new Ui(); // Initialize the user interface
        this.storage = new Storage(filePath); // Initialize storage with the given file path
        this.storage.setUp(); // Set up the storage (create file if not present)
        try {
            this.tasks = new TaskList(storage.load()); // Load tasks from storage
        } catch (SilverWolfException e) {
            this.ui.showLoadingError(); // Show an error if loading tasks fails
            this.tasks = new TaskList(); // Initialize an empty task list if loading fails
        } catch (IllegalArgumentException e) {
            this.ui.showFileError(); // Show an error if the file has errors
            this.tasks = new TaskList(); // Initialize an empty task list if loading fails
        }
    }

    /**
     * Runs the SilverWolf chatbot.
     * Displays the welcome message and enters a loop to continuously read user commands
     * until the exit command is issued.
     */
    public void run() {
        this.ui.showWelcome(); // Display the welcome message to the user
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = this.ui.readCommand(); // Read the user's command
                Command c = Parser.parse(fullCommand); // Parse the command into a Command object
                c.execute(this.tasks, this.ui, this.storage); // Execute the command
                isExit = c.isExit(); // Check if the command is an exit command
            } catch (SilverWolfException e) {
                this.ui.showError(e.getMessage()); // Show any errors that occur during execution
            }
        }
    }

    /**
     * The main method for running the SilverWolf chatbot application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        SilverWolf chatBot = new SilverWolf(FILE_PATH); // Create an instance of the chatbot
        chatBot.run(); // Run the chatbot

    }
}
