package dave;

import java.io.IOException;

import dave.command.Command;
import dave.exceptions.InvalidCommandException;
import dave.exceptions.InvalidDescriptionException;
import dave.parser.Parser;
import dave.storage.Storage;
import dave.task.TaskList;
import dave.ui.Ui;

/**
 * The main class for the Dave application, responsible for initializing components
 * and running the main program loop.
 */
public class Dave {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a Dave instance, initializing the user interface, task list, and storage components.
     *
     * @param filePath The file path where tasks will be stored and loaded from.
     */
    public Dave(String filePath) {
        this.ui = new Ui();
        this.tasks = new TaskList();
        this.storage = new Storage(filePath, this.tasks);
    }

    /**
     * Gets the response for a user input.
     *
     * @param input The user input.
     * @return The response from executing the command.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, storage, ui);
        } catch (InvalidCommandException e) {
            return e.getMessage();
        } catch (InvalidDescriptionException e) {
            return e.getMessage();
        } catch (IOException e) {
            return "An error occurred while trying to write to the file.";
        } catch (Exception e) {
            return "Unexpected error occurred.";
        }
    }
}
