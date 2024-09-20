package rose;

import java.io.IOException;

import rose.command.Command;

/**
 * The {@code Rose} class is the main entry point for the task management application.
 *
 * <p>This class initializes the necessary components, such as {@link Ui}, {@link Storage},
 * and {@link TaskList}, and orchestrates the application's main loop, handling user commands
 * and interactions.</p>
 */
public class Rose {

    private static final String FILE_PATH = "data/tasks.txt";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Creates an instance of the Rose application.
     * <p>
     * Initializes the user interface, storage, and task list components.
     * Loads tasks from the file, or starts with an empty task list if the file cannot be read.
     */
    public Rose() {
        ui = new Ui();
        storage = new Storage(FILE_PATH);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        } catch (RoseException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Processes a user input command and returns the result as a response.
     *
     * @param input The user input command.
     * @return The result of executing the command, or an error message if an exception occurs.
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            return command.execute(tasks, ui, storage);
        } catch (RoseException e) {
            return e.getMessage();
        }
    }
}


