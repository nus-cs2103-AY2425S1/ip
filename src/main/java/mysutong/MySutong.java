package mysutong;

import java.io.IOException;

/**
 * Main class for the MySutong application, which manages tasks.
 * This class is responsible for initializing the application, loading existing tasks from storage,
 * and handling user interactions through a command loop.
 */
public class MySutong {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a new instance of MySutong with a specified file path for storage.
     * Initializes the user interface, storage, and task list. It handles errors during the loading of tasks
     * and initializes an empty task list if necessary.
     *
     * @param filePath the path to the file where tasks are stored.
     */
    public MySutong(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList(); // Initialize with an empty list if loading fails
            ui.showError("Failed to load tasks from file: " + e.getMessage());
        } catch (Exception e) {
            ui.showError("An unexpected error occurred: " + e.getMessage());
            tasks = new TaskList(); // Safeguard: Initialize an empty list on any other exceptions
        }
    }

    /**
     * Runs the main loop of the application. Continuously reads user commands and executes them
     * using the Parser until the application is terminated. It handles any exceptions during command execution.
     */
    public void run() {
        ui.showWelcome();
        Parser parser = new Parser();
        while (true) {
            try {
                String fullCommand = ui.readCommand();
                parser.executeCommand(fullCommand, tasks, ui, storage);
            } catch (Exception e) {
                ui.showError("An unexpected error occurred during command execution: " + e.getMessage());
            }
        }
    }

    public String getResponse(String input) {
        Parser parser = new Parser();
        try {
            // Create a custom Ui to capture the output as a string
            Ui responseUi = new Ui();
            parser.executeCommand(input, tasks, responseUi, storage);
            return responseUi.getResponse();  // Get the captured output from Ui
        } catch (Exception e) {
            return "An error occurred: " + e.getMessage();
        }
    }

    /**
     * The entry point of the MySutong application.
     * Initializes and runs the application with a predefined path for task storage.
     *
     * @param args command-line arguments (not used).
     */
    public static void main(String[] args) {
        new MySutong("data/tasks.txt").run();
    }
}
