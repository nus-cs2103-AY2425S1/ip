package killua;

import java.io.IOException;

import killua.command.Command;
import killua.parser.Parser;
import killua.storage.Storage;
import killua.ui.Ui;
import killua.util.KilluaException;
import killua.util.TaskList;

/**
 * Represents the main application class for the Killua task manager.
 * It handles the initialization of the user interface (UI), storage, and task list,
 * and manages the main application loop where user commands are processed.
 */
public class Killua {

    // Path to save list data
    private static final String FILE_PATH = "./data/tasks.txt";
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;
    private boolean isRunning;

    /**
     * Constructs Killua instance.
     *
     */
    public Killua() {
        this.ui = new Ui();
        this.storage = new Storage(FILE_PATH);
        this.tasks = storage.load();
        this.isRunning = true;
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parseCommand(input);
            if (c.isExit()) {
                this.isRunning = false;
            }
            return c.execute(tasks, ui, storage);
        } catch (KilluaException | IOException e) {
            return "Error" + e.getMessage();
        }
    }

    /**
     * Generates a welcome message.
     */
    public String welcomeUser() {
        return ui.welcomeUser();
    }
    /**
     * Return running state.
     */
    public boolean isRunning() {
        return this.isRunning;
    }
}
