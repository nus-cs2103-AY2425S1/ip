package bangmang.command;

import bangmang.storage.Storage;
import bangmang.tasks.TaskList;
import bangmang.ui.Ui;
import bangmang.exception.InvalidCommandException;

/**
 * Represents an abstract command that can be executed.
 * Subclasses should implement the specific details of how the command is executed
 * and whether it signifies an exit command.
 */

public abstract class Command {
    /**
     * Executes the command based on the provided tasks, UI, and storage.
     *
     * @param tasks The list of tasks to be manipulated.
     * @param ui The user interface for interacting with the user.
     * @param storage The storage for saving/loading tasks.
     * @return A String representing the result of the command execution.
     * @throws InvalidCommandException If the command cannot be executed due to invalid data.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws InvalidCommandException;

    /**
     * Checks if the command represents an exit command.
     *
     * @return True if the command is an exit command, false otherwise.
     */
    public abstract boolean isExit();
}
