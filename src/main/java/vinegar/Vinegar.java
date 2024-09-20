package vinegar;

import vinegar.command.Command;
import vinegar.storage.Storage;
import vinegar.task.TaskList;
import vinegar.ui.Ui;

import java.io.IOException;

/**
 * The main class for the Vinegar chatbot application.
 * Manages interactions with the user, including task management and file I/O operations.
 * <p>
 * This class initializes the UI, Storage, and TaskList components and runs the main application loop.
 * The Vinegar chatbot supports commands such as adding tasks, marking tasks as done, and loading tasks from a file.
 */
public class Vinegar {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a Vinegar object with the specified file path.
     * Initializes the UI, storage, and task list.
     * Loads any existing tasks from the specified file.
     *
     * @param filePath The path to the task storage file.
     */
    public Vinegar(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            getResponse(ui.showLoadingError());
            tasks = new TaskList();
        }
    }

    /**
     * Runs the main application loop.
     * Continuously reads user commands, executes them, and interacts with the task list and storage.
     */
    public void run() {
        getResponse(ui.showWelcome());
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (VinegarException | IOException e) {
                getResponse(ui.showError(e.getMessage()));
            }
        }
    }

    /**
     * The main method that starts the Vinegar application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new Vinegar("./data/vinegar.txt").run();
    }

    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            //assert c != null : "Parsed command should not be null";
            return c.execute(tasks, ui, storage);
        } catch (VinegarException | IOException e) {
            return "Error: " + e.getMessage();
        }
    }
}
