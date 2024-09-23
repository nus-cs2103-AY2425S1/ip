package optimus;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * The main class for the Optimus task management application.
 * The Optimus class initializes the necessary components and runs the application.
 */
public class Optimus {
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
        assert ui != null : "Ui should not be null";
        assert storage != null : "Storage should not be null";
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
            return e.getMessage();
        }
    }

    /**
     * Used to retrieve ui from this class
     * @return ui
     */

    public Ui getUi () {
        return ui;
    }

    /**
     * Method required for Optimus to run but is not actually needed in actual program
     * @param args
     */
    public static void main(String[] args) {}


}