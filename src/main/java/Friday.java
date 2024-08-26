import friday.command.Command;
import friday.task.TaskList;
import friday.util.FridayException;
import friday.util.Parser;
import friday.util.Storage;
import friday.util.Ui;

import java.io.IOException;

/**
 * Represents the main application logic for the Friday task manager.
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
     * Runs the main loop of the application. Displays a welcome message, then repeatedly
     * reads user commands, parses them, and executes the corresponding {@link Command}.
     * Exits the loop and the application when a command indicating exit is executed.
     * Handles any exceptions that occur during command execution and displays appropriate error messages.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (FridayException | IOException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * The entry point of the application. Creates a new {@link Friday} instance and starts the application
     * by calling its {@link #run()} method.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new Friday().run();
    }
}
