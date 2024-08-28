package command;

import exception.DudeException;
import storage.Storage;
import storage.TaskList;
import ui.Ui;

/**
 * Represents a command that can be executed by the user.
 */
public abstract class Command {

    /**
     * Executes the command.
     *
     * @param tasks The task list to be listed.
     * @param ui The user interface to interact with the user.
     * @param storage The storage file to be updated.
     * @throws DudeException If there is an error executing the command.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DudeException;

    /**
     * Returns true if the command is an exit command.
     *
     * @return true if the command is an exit command, false otherwise.
     */
    public abstract boolean isExit();
}
