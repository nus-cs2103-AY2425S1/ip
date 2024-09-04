package killua;

import java.io.IOException;

import javafx.stage.Stage;
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

    private static final String FILE_PATH = "./data/tasks.txt";
    private final Storage storage;
    private final Stage stage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Constructs a Killua instance with the specified file path for task storage.
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public Killua(String filePath, Stage stage) {
        this.stage = stage;
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = storage.load();
        } catch (IOException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parseCommand(input);
            if (c.isExit()) {
                stage.close();
            }
            return c.execute(tasks, ui, storage);
        } catch (KilluaException | IOException e) {
            return e.getMessage();
        }
    }

    /**
     * Generates a welcome message.
     */
    public String welcomeUser() {
        return ui.welcomeUser();
    }
}
