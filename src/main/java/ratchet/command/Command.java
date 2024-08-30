package ratchet.command;

import ratchet.exception.RatchetException;
import ratchet.storage.Storage;
import ratchet.task.TaskList;
import ratchet.ui.Ui;

/**
 * Abstract class representing a command to be done by Ratchet.
 */
public abstract class Command {
    /**
     * Executes the command.
     *
     * @param storage Storage to for command to use.
     * @param tasks   Task list for command to use.
     * @param ui      Ui for command to use.
     * @throws RatchetException If there is an error during execution.
     */
    public abstract void execute(Storage storage, TaskList tasks, Ui ui) throws RatchetException;

    /**
     * Returns true if it is exit command and false otherwise.
     *
     * @return A boolean representing whether it is an exit command or not.
     */
    public abstract boolean isExit();
}
