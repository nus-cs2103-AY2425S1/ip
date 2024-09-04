package lexi;

import lexi.command.Command;
import lexi.exception.LexiException;
import lexi.parser.Parser;
import lexi.storage.Storage;
import lexi.task.TaskList;
import lexi.ui.Ui;

/**
 * The main class for the Lexi application.
 * This class initializes the application, loads the necessary resources, and runs the main loop.
 */
public class Lexi {

    private static final String filePath = "data/tasks.txt";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private String commandType;

    /**
     * Constructs a new Lexi application instance.
     * Initializes the UI, loads tasks from the specified file, and prepares the task list.
     */
    public Lexi() {
        try {
            ui = new Ui();
            storage = new Storage(Lexi.filePath);
            tasks = new TaskList(storage.load());
        } catch (LexiException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the Lexi application.
     * This method starts the main loop that reads commands from the user, processes them,
     * and executes the corresponding actions until the user decides to exit.
     */
    public String getResponse(String input) {
            try {
                Command c = Parser.parse(input);
                c.execute(tasks, ui, storage);
                this.commandType = c.getClass().getSimpleName();
                return c.getString();
            } catch (LexiException e) {
                return ui.showError(e.getMessage());
            }
    }
    public String getCommandType() {
        return commandType;
    }
}
