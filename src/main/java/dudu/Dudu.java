package dudu;

import java.io.IOException;

import dudu.command.Command;
import dudu.exception.DuduException;
import dudu.exception.InvalidFormatException;
import dudu.exception.MissingDateTimeException;
import dudu.exception.MissingDescriptionException;
import dudu.utils.Parser;
import dudu.utils.Storage;
import dudu.utils.TaskList;
import dudu.utils.UI;

/**
 * Represents the main class Dudu chatbot.
 */
public class Dudu {
    private TaskList tasks;
    private UI ui = new UI();
    private Storage storage;

    /**
     * Constructs Dudu.
     *
     * @param filePath File path of the file containing existing tasks.
     */
    public Dudu(String filePath) {
        storage = new Storage(filePath);
    }

    /**
     * Loads tasks from local file.
     *
     * @return Error message if there is an error reading the file.
     */
    public String loadLocalTasks() {
        try {
            tasks = new TaskList(storage.load());
            return "";
        } catch (DuduException exception) {
            tasks = new TaskList();
            return "There was an error reading tasks from local file";
        }
    }

    /**
     * Returns a welcome message.
     */
    public String welcomeUser() {
        return ui.getWelcomeMessage();
    }

    /**
     * Returns a response to a user input.
     *
     * @param input User input.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (IOException e) {
            return ui.getErrorMessage(e);
        } catch (MissingDescriptionException e) {
            return ui.getErrorMessage(e);
        } catch (InvalidFormatException e) {
            return ui.getErrorMessage(e);
        } catch (DuduException e) {
            return ui.getErrorMessage(e);
        } catch (MissingDateTimeException e) {
            return ui.getErrorMessage(e);
        }
    }
}
