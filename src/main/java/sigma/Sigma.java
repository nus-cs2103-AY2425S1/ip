package sigma;

import java.io.File;

import javafx.application.Platform;
import sigma.command.Command;
import sigma.exception.SigmaException;
import sigma.utils.Parser;
import sigma.utils.Storage;
import sigma.utils.TaskList;
import sigma.utils.Ui;

/**
 * Represents the main class of Sigma.
 * Initializes the Ui, Storage, and TaskList.
 */
public class Sigma {

    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;
    private final File data;
    private String commandType;

    /**
     * Constructor for Sigma.
     * Initializes the Ui, Storage, and TaskList.
     */
    public Sigma() {
        this.ui = new Ui();
        storage = new Storage("data.txt");
        this.data = new File("data.txt");
        tasks = new TaskList(storage.read(data));
    }

    /**
     * Constructor for Sigma.
     * Initializes the Ui, Storage, and TaskList.
     *
     * @param filePath
     */
    public Sigma(String filePath) {
        this.ui = new Ui();
        storage = new Storage(filePath);
        this.data = new File(filePath);
        tasks = new TaskList(storage.read(data));
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            commandType = c.toString();
            assert tasks != null : "TaskList cannot be null";
            assert storage != null : "Storage cannot be null";
            assert ui != null : "Ui cannot be null";
            String response = c.execute(tasks, ui, storage);
            if (c.isExit()) {
                Platform.exit();
            }
            return response;
        } catch (SigmaException e) {
            return e.getMessage();
        } catch (NumberFormatException e) {
            return "What the sigma? I need a number!";
        }
    }

    public String getCommandType() {
        return commandType;
    }

    public Ui getUi() {
        return this.ui;
    }
}







