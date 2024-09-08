package optimus;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * The main class for the Optimus task management application.
 * The Optimus class initializes the necessary components and runs the application.
 */
public class Optimus {

    private static final String FILE_PATH = "./data/optimus.txt";  // Default file path for storing tasks.
    private Storage storage;  // Handles loading and saving tasks.
    private TaskList tasks;  // Manages the list of tasks.
    private Ui ui;  // Handles user interaction.

    /**
     * Constructs an object with the specified file path for storing tasks.
     */
    public Optimus(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadFile());
        } catch (FileNotFoundException | OptimusException e) {
            ui.printError(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Starts the main loop of the Optimus application, reading and processing user commands.
     */
    public String getResponse(String input) {
        try {
            // Parse the user's input command and get the response string
            return Parser.parseCommand(input, tasks, ui, storage);
        } catch (OptimusException | IOException e) {
            // Return the error message if an exception occurs
            return "Error: " + e.getMessage();
        }
    }


}