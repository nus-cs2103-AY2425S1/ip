package bopes;

import java.util.ArrayList;
import java.io.File;

import bopes.exception.BopesException;
import bopes.task.TaskList;

/**
 * Main class for the Bopes application.
 */
public class Bopes {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a Bopes object and initializes the task list and UI.
     * 
     * @param filePath The path to the file used for storage.
     */
    public Bopes() {
        ui = new Ui();
         // Get the user's home directory
         String homeDirectory = System.getProperty("user.home");
        
         // Define the path to store the file inside a subdirectory, e.g., Bopes
         String filePath = homeDirectory + File.separator + "Bopes" + File.separator + "tasks.txt";
 
         // Ensure the directory exists
         File bopesDir = new File(homeDirectory + File.separator + "Bopes");
         if (!bopesDir.exists()) {
             bopesDir.mkdir();
         }
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTasks());
            ui.showTasks(tasks);
        } catch (BopesException e) {
            ui.showLoadingError();
            tasks = new TaskList(new ArrayList<>());
        }
    }

    public boolean isValidCommand(String input) {
        if (input.equals("hello") || input.equals("bye")) {
            return true;
        }
        return false; 
    }

    /**
     * Handles user input and returns the response from Bopes.
     * 
     * @param input The user input.
     * @return The response from Bopes.
     */
    public String getResponse(String input) {
        if (isValidCommand(input)) {
            return "This is a valid command response!";
        } else {
            return "Error: Invalid command.";
        }
    }

    public CommandResult parseInput(String input) {
        return Parser.parse(input, tasks, storage);  // Use the tasks and storage already initialized
    }

    /**
     * The main method to launch the Bopes application.
     * 
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        // Initialize the JavaFX application
        Main.main(args);  // This launches the JavaFX application
    }
}
