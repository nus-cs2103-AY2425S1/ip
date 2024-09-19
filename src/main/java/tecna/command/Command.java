package tecna.command;

import tecna.collection.TaskList;
import tecna.storage.Storage;
import tecna.ui.Ui;

/**
 * Represents a generic command.
 *
 * @author Adapted from Feng1231.
 */
public abstract class Command {
    protected String message;

    /**
     * Constructs a Command instance.
     *
     * @param message The whole command input in String.
     */
    public Command(String message) {
        this.message = message;
    }

    /**
     * Executes the command.
     * Executes if it is valid
     * and alerts if it is invalid.
     *
     * @return A response to the user.
     */
    public String execute(TaskList taskList, Storage storage, Ui ui) {
        assert taskList != null;
        assert storage != null;
        assert ui != null;
        return "";
    };

}
