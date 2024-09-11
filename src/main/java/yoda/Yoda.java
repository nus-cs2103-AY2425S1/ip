package yoda;

import yoda.commands.Command;
import yoda.exceptions.YodaException;

/**
 * Main class for the Yoda application, which coordinates the user interface,
 * task management, and data storage.
 */
public class Yoda {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser = new Parser();

    /**
     * Initializes the Yoda application with the specified file path for storage.
     * Sets up the user interface, storage, and attempts to load tasks from the file.
     * If loading tasks fails, initializes an empty task list and displays an error message.
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public Yoda(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (YodaException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the Yoda application, handling user input and executing commands.
     * Displays a welcome message, processes user input in a loop, and performs
     * actions based on the commands received. Saves the task list to storage after
     * each command execution.
     */
    public String getResponse(String input) {
        try {
            Command command = parser.handle(input, tasks);
            return command.run();
        } catch (YodaException e) {
            return e.getMessage();
        }
    }
}
