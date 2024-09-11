import Alex.Command.Command;
import Alex.Exceptions.AlexException;
import Alex.Parser.Parser;
import Alex.Storage.Storage;
import Alex.Task.TaskList;
import Alex.Ui.Ui;

/**
 * Represents the main application class that runs the Alex chatbot.
 * Handles initialization, user interactions, and command processing.
 */
public class Alex {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Initializes the Alex chatbot with the specified file path for storage.
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public Alex(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (AlexException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }
    /**
     * Processes the user input and returns the response to be shown in the GUI.
     *
     * @param input The user's input.
     * @return The response from Alex.
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input); // Parse the user's input into a command
            command.execute(tasks, ui, storage); // Execute the command (Ui updates will be appended)
            return ui.getOutput(); // Retrieve the response from Ui
        } catch (AlexException e) {
            ui.showError(e.getMessage()); // Append error message to Ui
            return ui.getOutput(); // Return the error message
        }
    }



    /**
     * Entry point of the application.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        new Alex("data/tasks.txt");
    }
}

