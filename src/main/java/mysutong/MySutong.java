package mysutong;

import java.io.IOException;

/**
 * Main class for the MySutong application, which manages tasks.
 * This class is responsible for initializing the application, loading existing tasks from storage,
 * and handling user interactions through a command loop or a GUI.
 */
public class MySutong {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a new instance of MySutong with a specified file path for storage.
     * Initializes the user interface, storage, and task list. If there is an error loading the tasks from storage,
     * an empty task list will be initialized, and an error will be shown in the UI.
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
     * Runs the main loop of the application.
     * Continuously reads user commands from the CLI, delegates execution of the commands to the {@link Parser},
     * and displays the output through the {@link Ui}. This loop continues until the application is terminated.
     * It handles any exceptions that may occur during command execution and displays them via the UI.
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

    /**
     * Gets a response from MySutong for the given user input.
     * This method is used in the GUI to process user input, delegate the command to the {@link Parser},
     * and return the output as a string. It captures any errors that occur and returns them as part of the response.
     *
     * @param input The user input/command to be processed.
     * @return The response from MySutong after executing the given command.
     */
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
     * This method starts the CLI version of MySutong.
     *
     * @param args command-line arguments (not used).
     */
    public static void main(String[] args) {
        new MySutong("data/tasks.txt").run();
    }
}
