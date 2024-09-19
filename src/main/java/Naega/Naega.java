package Naega;

import Naega.Command.Command;
import Naega.Parser.Parser;
import Naega.Storage.Storage;
import Naega.Task.TaskList;
import Naega.Ui.Ui;

import java.io.IOException;

/**
 * The main class for the Naega application.
 * Handles initialization, command processing, and application flow.
 */
public class Naega {

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Creates a new Naega instance with the specified file path.
     * Initializes the user interface and storage. Loads tasks from the storage
     * if available, otherwise initializes a new TaskList.
     *
     * @param filePath the path to the file where tasks are stored
     */

    public Naega(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (NaegaException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Starts the main loop of the application. Continuously reads commands from
     * the user, parses and executes them, and handles errors. The loop terminates
     * when an exit command is encountered.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command command = Parser.parse(fullCommand);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (NaegaException e) {
                ui.showError(e.getMessage());
            } catch (IOException e) {
                // Consider logging the exception or providing a more specific message
                throw new RuntimeException("Unexpected error occurred during IO operations.", e);
            } finally {
                ui.showLine();
            }
        }
    }
    /**
     * Processes the input command and returns the appropriate response.
     * This method is called in the GUI to handle user input.
     *
     * @param input the user's input command
     * @return the response from Naega
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            return command.execute(tasks, ui, storage);
        } catch (NaegaException | IOException e) {
            return "Error: " + e.getMessage();
        }
    };

    /**
     * The entry point of the application. Creates a new Naega instance with
     * the specified file path and starts the application by calling the run method.
     *
     * @param args command-line arguments (not used)
     */

    public static void main(String[] args) {
        new Naega("data/tasks.txt").run();
    }
}