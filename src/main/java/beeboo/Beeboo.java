package beeboo;

import beeboo.command.Command;
import beeboo.components.Parser;
import beeboo.components.Storage;
import beeboo.components.TaskList;
import beeboo.components.Ui;
import beeboo.exception.BeeBooExceptions;
import beeboo.exception.InvalidCommandException;
import javafx.scene.image.Image;

/**
 * The BeeBoo class represents the main application logic for the BeeBoo task management
 * system. It handles initialization, user interactions, command processing, and task management.
 */
public class Beeboo {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private static Image beebooImage = new Image(Beeboo.class.getResourceAsStream("/images/Beeboo.jpg"));

    /**
     * Constructs a BeeBoo instance with the specified file path for storage.
     * Initializes the user interface, storage, and task list. If loading the tasks fails,
     * an empty task list is created.
     *
     * @param filePath the path to the file where tasks are stored
     */
    public Beeboo(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (BeeBooExceptions e) {
            ui.loadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Generates a response for the user's chat message.
     *
     * @param input the user's input message
     * @return the response from BeeBoo
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parseCommand(input);
            assert c != null : "Command should not be null";
            return c.execute(tasks, ui, storage);
        } catch (InvalidCommandException e) {
            return "Invalid Command! Me no understand";
        } catch (BeeBooExceptions e) {
            return e.toString();
        }
    }

    /**
     * Returns the image used for BeeBoo's avatar.
     *
     * @return BeeBoo's image
     */
    public static Image getBeebooImage() {
        return beebooImage;
    }
}
