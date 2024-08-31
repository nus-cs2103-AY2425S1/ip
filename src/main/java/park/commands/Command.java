package park.commands;

import park.exceptions.ParkException;
import park.storage.Storage;
import park.storage.TaskList;
import park.ui.Ui;

/**
 * Represents a command to be executed.
 */
public abstract class Command {

    /**
     * Makes the necessary changes to the list of tasks and saved file based on the command.
     * Prints message shown to user using ui.
     *
     * @param tasks TaskList object.
     * @param ui Ui object.
     * @param storage Storage object.
     * @throws ParkException If command cannot be executed.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws ParkException;

    /**
     * Checks if this command is an exit command.
     *
     * @return True if this command is an exit command, false otherwise.
     */
    public abstract boolean isExit();
}
