package command;

import exception.DynamikeException;
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
     * @throws DynamikeException If there is an error executing the command.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DynamikeException;

    /**
     * Returns true if the command is an exit command.
     *
     * @return true if the command is an exit command, false otherwise.
     */
    public abstract boolean isExit();
}
