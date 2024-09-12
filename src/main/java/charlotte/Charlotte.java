package charlotte;

import charlotte.command.Command;
import charlotte.exception.CharlotteException;
import charlotte.parser.Parser;
import charlotte.storage.Storage;
import charlotte.task.TaskList;
import charlotte.ui.Ui;

/**
 * Represents the main application class for Charlotte.
 */
public class Charlotte {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a Charlotte chatbot instance with the specified file path for data storage.
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public Charlotte(String filePath) {
        ui = new Ui();
        assert ui != null : "UI object should not be null";
        storage = new Storage(filePath);
        assert storage != null : "Storage object should not be null";
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (CharlotteException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
        assert tasks != null : "TaskList object should not be null";
    }

    /**
     * Starts and runs the Charlotte chatbot.
     * <p>
     * This method displays the welcome message, then enters a loop where it continuously reads user commands,
     * parses them, and executes the corresponding commands. The loop continues until an exit command is received.
     * If any exceptions are encountered during command execution, an error message is displayed to the user.
     * </p>
     */
    public void run() {
        ui.printWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String command = ui.readCommand();
                assert command != null && !command.isEmpty() : "Command input should not be null or empty";
                Command c = Parser.parse(command);
                assert c != null : "Parsed command should not be null";
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (CharlotteException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        try {
            assert input != null && !input.isEmpty() : "Input in getResponse should not be null or empty";
            Command c = Parser.parse(input);
            assert c != null : "Parsed command in getResponse should not be null";
            String response = c.execute(tasks, ui, storage);
            return response;
        } catch (CharlotteException e) {
            return ui.showError(e.getMessage());
        }
    }

    /**
     * The main method to start the Charlotte chatbot.
     * <p>
     * This method creates an instance of the Charlotte chatbot with the specified data file path and
     * starts the application by calling the run method.
     * </p>
     *
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        new Charlotte("data/charlotte.txt").run();
    }
}
