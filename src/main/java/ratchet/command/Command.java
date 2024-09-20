package ratchet.command;

import ratchet.exception.InvalidCommandArgumentIndex;
import ratchet.exception.RatchetException;
import ratchet.storage.Storage;
import ratchet.task.TaskList;
import ratchet.ui.Ui;

/**
 * Abstract class representing a command to be done by Ratchet.
 */
public abstract class Command {
    /**
     * Executes a command.
     *
     * @param storage Storage to for command to use.
     * @param tasks   Task list for command to use.
     * @param ui      Ui for command to use.
     * @return String to be displayed after execution.
     * @throws RatchetException If there is an error during execution.
     */
    public abstract String execute(Storage storage, TaskList tasks, Ui ui) throws InvalidCommandArgumentIndex;
}
