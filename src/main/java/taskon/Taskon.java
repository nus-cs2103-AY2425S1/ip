package taskon;

import taskon.commands.Command;
import taskon.exception.TaskonException;
import taskon.parser.Parser;
import taskon.storage.Storage;
import taskon.task.TaskList;
import taskon.ui.Ui;

/**
 * Represents the main class for the Taskon application.
 * <p>
 * Handles initialization and facilitates interaction between
 * different components of the Taskon application.
 * </p>
 */
public class Taskon {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a Taskon object and initializes the application with the default storage file path.
     */
    public Taskon() {
        this("./data/taskon.txt");
    }

    /**
     * Constructs a Taskon object and initializes the application with the specified storage file path.
     *
     * @param filePath The file path where tasks are stored.
     */
    public Taskon(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (TaskonException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Processes the input from the user and returns the response.
     * <p>
     * Parses the user input into a command, executes the command, and returns the response. Exits the application
     * if the exit command is encountered.
     * </p>
     *
     * @param input The user input to process.
     * @return The response to the user input.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (TaskonException e) {
            return ui.showError(e.getMessage());
        }
    }

    /**
     * Runs the Taskon application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new Taskon("./data/taskon.txt");
    }
}
