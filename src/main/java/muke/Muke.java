package muke;

import java.io.IOException;

import muke.command.Command;
import muke.exception.EmptyDescriptionException;
import muke.exception.UnknownCommandException;
import muke.parser.Parser;
import muke.storage.Storage;
import muke.task.TaskList;
import muke.ui.Ui;

/**
 * Represents the main class of the Muke application, which manages tasks
 * and handles user interactions through a command-line interface.
 */
public class Muke {
    private static final String FILE_PATH = "./muke/data/muke.txt";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a Muke object, initializing the user interface, storage, and task list.
     * Attempts to load tasks from a specified file path.
     */
    public Muke() {
        ui = new Ui();
        assert ui != null : "UI should be initialized";

        storage = new Storage(FILE_PATH);
        assert storage != null : "Storage should be initialized";

        try {
            tasks = new TaskList(storage.load());
            assert tasks != null : "Tasks should be loaded properly";

        } catch (IOException e) {
            ui.showError("Error loading tasks: " + e.getMessage());
            tasks = new TaskList();
            assert tasks != null : "Tasks should be initialized even on loading failure";
        }
    }

    /**
     * Runs the main loop of the Muke application, handling user input and executing commands
     * until the user decides to exit.
     */
    public void run() {
        ui.showWelcome();

        boolean isRunning = true;
        while (isRunning) {
            try {
                String input = ui.readCommand();
                assert input != null && !input.trim().isEmpty() : "Input should not be null or empty";

                Command command = Parser.parse(input);
                assert command != null : "Parser should return a valid command"; 

                command.execute(tasks, ui, storage);
                isRunning = !command.isExit();
            } catch (EmptyDescriptionException | UnknownCommandException e) {
                ui.showError(e.getMessage());
            } catch (IOException e) {
                ui.showError("An error occurred while saving your tasks.");
            }
        }
    }

    public String getResponse(String input) {
        assert input != null : "Input should not be null";
        try {
            // Parse and execute the command
            Command command = Parser.parse(input);
            assert command != null : "Parser should return a valid command";
            
            command.execute(tasks, ui, storage);
            return ui.getLastResponse(); // Assuming `Ui` captures the last response
        } catch (EmptyDescriptionException | UnknownCommandException e) {
            return e.getMessage(); // Return error messages if command is invalid
        } catch (IOException e) {
            return "An error occurred while processing your input.";
        }
    }

    // Add this method to return a greeting message
    public String getGreeting() {
        return "Hello! I am Muke. How can I assist you today?";
    }

    // Add this method to check if Muke is running, adjust logic as necessary
    public boolean isRunning() {
        // Example logic, adjust as needed based on your application's requirements
        boolean running = true;
        assert running : "Muke should be running";
        return true;  // Assume Muke is always running for now
    }
    /**
     * The main method that launches the Muke application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new Muke().run();
    }
}