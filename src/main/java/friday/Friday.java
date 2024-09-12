package friday;

import java.io.IOException;

import friday.command.Command;
import friday.command.Parser;
import friday.task.TaskList;
import friday.util.FridayException;
import friday.util.Storage;
import friday.util.Ui;

/**
 * Represents the main application logic for the friday.Friday task manager.
 * Handles task management, user input parsing, and file I/O.
 */
public class Friday {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Constructs a {@link Friday} instance, initializes the user interface,
     * sets up storage for tasks, and attempts to load existing tasks from storage.
     * If loading tasks fails, initializes an empty task list and displays an error message.
     */
    public Friday() {
        ui = new Ui();
        storage = new Storage();
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Gets welcome message
     *
     * @return The welcome message from Duke.
     */
    public String getWelcome() {
        return ui.showWelcome();
    }

    /**
     * Generates a response for the user's chat message.
     *
     * @param input The user's input as a string.
     * @return The response generated by Duke.
     */
    public String getResponse(String input) {
        try {
            return handleUserCommand(input);
        } catch (FridayException | IOException e) {
            return "Error: " + e.getMessage();
        }
    }

    private String handleUserCommand(String input) throws FridayException, IOException {
        Command c = Parser.parse(input);
        assert c != null : "Parsed command should not be null";
        return c.execute(tasks, ui, storage);
    }
}
