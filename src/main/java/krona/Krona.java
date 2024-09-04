package krona;

import krona.command.Command;
import krona.exception.KronaException;
import krona.parser.Parser;
import krona.storage.Storage;
import krona.task.TaskList;
import krona.ui.Ui;

/**
 * Main class for Krona chatbot
 */
public class Krona {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs the new Krona.
     * Initializes the user interface, storage, and task list.
     * Attempts to load existing tasks from the specified file path.
     *
     * @param filePath The file path where tasks are stored.
     */
    public Krona(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (KronaException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Processes the user input and returns the appropriate response from the Krona chatbot.
     *
     * This method takes the user's input, parses it to determine the command, and executes
     * the command on the task list. The method returns the resulting response string, which
     * is displayed in the GUI or terminal.
     *
     * @param input The user input in the form of a String.
     * @return A String representing Krona's response to the user, including success or error
     *         messages.
     * @throws KronaException if any error occurs during the execution of the command.
     */
    public String getResponse(String input) {
        Command c = Parser.parse(input);
        try {
            c.execute(tasks, ui, storage);
            // Return the combined message
            return ui.getCombinedMessage();
        } catch (KronaException e) {
            return "Error: " + e.getMessage();
        }
    }
}
