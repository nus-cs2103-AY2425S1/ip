package dudu;

import java.io.IOException;
import java.time.format.DateTimeParseException;

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
 * Represents the main class of the chatbot.
 */
public class Dudu {
    private TaskList tasks;
    private UI ui;
    private Storage storage;

    /**
     * The constructor for a chatbot instance
     *
     * @param filePath The file path of the file containing existing tasks
     */
    public Dudu(String filePath) {
        ui = new UI();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DuduException exception) {
            tasks = new TaskList();
        }
    }

    /**
     * Returns a welcome message
     *
     * @return Welcome message
     */
    public String welcomeUser() {
        return ui.getWelcomeMessage();
    }

    /**
     * Returns a response to a user input
     *
     * @param input User input
     * @return Response to user
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (IOException e) {
            return ui.showError(e);
        } catch (MissingDescriptionException e) {
            return ui.showError(e);
        } catch (InvalidFormatException e) {
            return ui.showError(e);
        } catch (DateTimeParseException e) {
            return ui.showError(e);
        } catch (MissingDateTimeException e) {
            return ui.showError(e);
        }
    }
}
