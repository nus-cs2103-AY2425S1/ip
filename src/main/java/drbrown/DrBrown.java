package drbrown;

import drbrown.command.Command;
import drbrown.utils.DrBrownException;
import drbrown.utils.Parser;
import drbrown.utils.Storage;
import drbrown.utils.TaskList;
import drbrown.utils.Ui;

/**
 * The main class for the DrBrown application, which is a task management program.
 * It handles the initialization, interaction with the user, and execution of commands.
 */
public class DrBrown {

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Constructs a new DrBrown instance with the specified file path for storage.
     * Initializes the UI, loads the task list from storage, and handles any exceptions during loading.
     * If loading fails, an empty task list is initialized.
     */
    public DrBrown() {
        ui = new Ui();
        storage = new Storage("data/DrBrown.txt");
        try {
            tasks = storage.load();
        } catch (DrBrownException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Returns a greeting message to be displayed in the GUI.
     *
     * @return A string containing the greeting message.
     */
    public String showGreeting() {
        return ui.showGreeting();
    }

    /**
     * Processes user input and returns the output message after executing the corresponding command.
     * If an exception occurs, an error message is returned.
     *
     * @param userInput The input provided by the user.
     * @return A string containing the result of the command execution or an error message.
     */
    public String showOutput(String userInput) {
        assert userInput != null : "User input should not be null";
        try {
            Command command = Parser.parse(userInput);
            return command.executeCommand(tasks, ui, storage);
        } catch (DrBrownException e) {
            return ui.showError(e.getMessage());
        }
    }

    /**
     * The main method to start the DrBrown application.
     * It creates a new instance of DrBrown and runs the application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new DrBrown();
    }
}
