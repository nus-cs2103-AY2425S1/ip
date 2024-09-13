package command;

import exception.DynamikeException;
import storage.Storage;
import storage.TaskList;
import ui.Ui;

/**
 * Represents a command that is invalid.
 */
public class InvalidCommand extends Command {
    /**
     * Throws a DynamikeException to indicate that the command is invalid.
     *
     * @param tasks The task list to be listed.
     * @param ui The user interface to interact with the user.
     * @param storage The storage file to be updated.
     * @throws DynamikeException To indicate that the command is invalid.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DynamikeException {
        throw new DynamikeException("I'm sorry, but I don't know what that means :(");
    }

    /**
     * Returns false because this is not an exit command.
     *
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
