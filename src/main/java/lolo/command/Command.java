package lolo.command;

import lolo.Ui;
import lolo.LoloException;
import lolo.storage.Storage;
import lolo.task.TaskList;

/**
 * Represents an abstract command in the Lolo application.
 * Commands define the actions that can be executed on the task list,
 * and they interact with the user interface and storage components.
 */
public abstract class Command {

    /**
     * Executes the command using the provided task list, user interface, and storage.
     * This method must be implemented by subclasses to perform specific actions.
     *
     * @param tasks The list of tasks to be modified by the command.
     * @param ui The user interface to interact with the user.
     * @param storage The storage to read from or write to.
     * @throws LoloException If there is an error during execution.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws LoloException;

    /**
     * Indicates whether this command is an exit command.
     * Subclasses can override this method to return true if the command should terminate the application.
     *
     * @return true if this command is an exit command, false otherwise.
     */
    public boolean isExit() {
        return false;
    }
}
