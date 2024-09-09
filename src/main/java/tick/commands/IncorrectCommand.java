package tick.commands;

import tick.exceptions.TickException;
import tick.storage.Storage;
import tick.storage.TaskList;
import tick.ui.Ui;

/**
 * Represents a command that is not recognised by Tick.
 */
public class IncorrectCommand extends Command {

    /**
     * Throws a TickException to inform the user that the command is not recognised.
     *
     * @param tasks TaskList where tasks are stored.
     * @param ui The user interface to display information to the user.
     * @param storage The storage file to be updated.
     * @throws TickException If the command is not recognised.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws TickException {
        throw new TickException("I don't know what that means!");
    }

    /**
     * Returns false as the command is not an exit command.
     *
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
