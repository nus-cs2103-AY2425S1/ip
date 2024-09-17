/**
 * BangMang is the main class for the BangMang chatbot application.
 * It manages the interaction between the user, tasks, and storage system.
 * The application loads tasks from storage, parses user input commands, 
 * and provides appropriate responses through its UI.
 *
 * Author: Chooi Shao Xian
 */
package bangmang;

import bangmang.command.Command;
import bangmang.exception.InvalidCommandException;
import bangmang.parser.Parser;
import bangmang.storage.Storage;
import bangmang.tasks.TaskList;
import bangmang.ui.Ui;

public class BangMang {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for BangMang class.
     * Initializes the UI, loads tasks from storage, and handles any loading errors.
     *
     * @param filePath The file path where tasks are stored.
     */
    public BangMang(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (InvalidCommandException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }

        // Assert that tasks should not be null
        assert tasks != null : "List of tasks should not be null";
    }

    /**
     * Returns a greeting message when the application starts.
     *
     * @return A welcome message string.
     */
    public String getGreeting() {
        try {
            return ui.showWelcome();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    /**
     * Processes user input and returns the corresponding response.
     *
     * @param input The user input command string.
     * @return The response message string.
     */
    public String getResponse(String input) {
        // Assert that input should not be null or empty
        assert input != null && !input.isEmpty() : "Input should not be null or empty";

        try {
            Command command = Parser.parse(input);

            // Assert that command should not be null
            assert command != null : "Command should not be null";

            String response = command.execute(tasks, ui, storage);
            return response;
        } catch (InvalidCommandException e) {
            return e.getMessage();
        }
    }
}
