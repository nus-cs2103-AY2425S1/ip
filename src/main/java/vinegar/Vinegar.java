package vinegar;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.util.Duration;
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
     */
    public Vinegar() {
        ui = new Ui();
        storage = new Storage();
        try {
            tasks = new TaskList(storage.load());
            // Assert that the task list is not null after loading
            assert tasks != null : "TaskList should not be null after loading from storage.";
        } catch (IOException e) {
            getResponse(ui.showLoadingError());
            tasks = new TaskList();
            // Assert that the task list is initialized even if loading fails
            assert tasks != null : "TaskList should be initialized even if storage loading fails.";
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
                // Assert that the full command is not empty or null
                assert fullCommand != null && !fullCommand.trim().isEmpty() : "Full command should not be null or empty.";

                Command c = Parser.parse(fullCommand);
                // Assert that the command was successfully parsed
                assert c != null : "Parsed command should not be null.";

                String commandResponse = c.execute(tasks, ui, storage);
                getResponse(commandResponse);

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
    /**
    public static void main(String[] args) {
        new Vinegar("./data/vinegar.txt").run();
    }

    /**
     * Processes the user input and returns the response.
     *
     * @param input The user input as a string.
     * @return The response generated by the chatbot.
     */
    public String getResponse(String input) {
        try {
            // Assert that the input is not null or empty before parsing
            assert input != null && !input.trim().isEmpty() : "Input should not be null or empty.";

            Command c = Parser.parse(input);
            // Assert that the command is not null after parsing
            assert c != null : "Parsed command should not be null.";

            return c.execute(tasks, ui, storage);
        } catch (VinegarException | IOException e) {
            return "Error: " + e.getMessage();
        }
    }
}
