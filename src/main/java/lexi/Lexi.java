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

            // Postcondition: Ensure storage and ui are initialized before loading tasks
            assert storage != null : "Storage should be initialized.";
            assert ui != null : "UI should be initialized.";

            tasks = new TaskList(storage.load());
        } catch (LexiException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }

        // Postcondition: Ensure tasks is initialized
        assert tasks != null : "Tasks should be initialized.";
    }

    /**
     * Processes user input and returns Lexi's response.
     *
     * @param input The user's input command as a string.
     * @return Lexi's response after processing the command.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);

            // Postcondition: Ensure command is parsed correctly
            assert c != null : "Parsed command should not be null.";

            this.commandType = c.getClass().getSimpleName();
            c.execute(tasks, ui, storage);
            return c.getString();
        } catch (LexiException e) {
            return ui.showError(e.getMessage());
        }
    }

    /**
     * Returns the type of the last command executed.
     *
     * @return The command type as a string.
     */
    public String getCommandType() {
        return commandType;
    }

    /**
     * Returns the greeting message.
     *
     * @return The greeting message as a string.
     */
    public String getGreeting() {
        return ui.showGreeting();
    }
}
