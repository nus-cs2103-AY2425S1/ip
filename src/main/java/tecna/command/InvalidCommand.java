package tecna.command;

import tecna.collection.TaskList;
import tecna.exception.WrongFormatException;
import tecna.storage.Storage;
import tecna.ui.Ui;

/**
 * Represents a Command of type InvalidCommand.
 *
 * @author Adapted from Feng1231.
 */
public class InvalidCommand extends Command {
    /**
     * Constructs an InvalidCommand instance.
     *
     * @param message The whole command input in String.
     */
    public InvalidCommand(String message) {
        super(message);
    }

    @Override
    public String execute(TaskList taskList, Storage storage, Ui ui) {
        super.execute(taskList, storage, ui);
        return ui.printInvalidCmdError();
    }
}
