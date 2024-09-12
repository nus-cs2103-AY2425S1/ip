package sage;

import java.io.IOException;

import sage.Command.Command;
import sage.List.TaskList;

/**
 * Sage class for managing the Sage application.
 * It initialises the user interface, storage, and task list, and handles user input.
 */
public class Sage {
    private static TaskList tasks;
    private static Ui ui;
    private static Storage storage;

    /**
     * Constructs a Sage object and initializes the user interface, storage, and task list.
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public Sage(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (IOException | SageException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    public String getWelcome() {
        return ui.showWelcome();
    }

    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            return command.execute(tasks, ui, storage);
        } catch (SageException e) {
            return "Error: " + e.getMessage();
        }
    }
}
