package Buu;

import java.util.List;

import javafx.application.Platform;


/**
 * The main class for the GPT application, which handles the initialization of the
 * user interface, storage, and task list, and runs the main application loop.
 */
public class Buu {
    private Ui ui;
    private Storage storage;
    private TaskList taskList;

    /**
     * Constructs a new GPT application with a default file path for task storage.
     */
    public Buu() {
        String filePath = "data/tasks.txt"; // Default file path
        ui = new Ui();
        storage = new Storage(filePath);
        taskList = new TaskList(storage.loadTasks());
        // Show the welcome message when the Buu application starts
        ui.showWelcomeMessage("Buu"); // This adds the welcome message
    }

    /**
     * Processes the input command and returns the corresponding response.
     */
    public String getResponse(String input) {
        // Assert that the input is not null
        assert input != null : "Input cannot be null";
        try {
            // Only clear the buffer if there is an actual input (i.e., not the empty welcome message case)
            if (!input.isEmpty()) {
                ui.clearOutputBuffer();
            }
            // Only add welcome message if no input is provided
            if (input.isEmpty()) {
                return String.join("\n", ui.getOutputBuffer());
            }
            // Parse and execute the command
            Command command = Parser.parseCommand(input);
            command.execute(taskList, ui, storage);

            // Check if the command signals an exit
            if (command.isExit()) {
                Platform.exit(); // Exit the JavaFX application
                return "Goodbye!";
            }

            // Collect the messages that were output to the Ui
            List<String> output = ui.getOutputBuffer();
            StringBuilder response = new StringBuilder();

            // Concatenate the output messages
            for (String msg : output) {
                response.append(msg).append("\n");
            }

            return response.toString().trim(); // Return the full output to display in the GUI
        } catch (TaskException exception) {
            return "Error: " + exception.getMessage();
        } catch (Exception e) {
            return "An unexpected error occurred: " + e.getMessage();
        }
    }
}
