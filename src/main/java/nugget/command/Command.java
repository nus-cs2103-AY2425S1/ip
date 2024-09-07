package nugget.command;

import nugget.exception.NuggetException;
import nugget.Storage;
import nugget.TaskList;
import nugget.Ui;

/**
 * Represents a command that can be executed to perform an action on a {@link TaskList}.
 * This interface is implemented by all commands in the application.
 */
public interface Command {
    /**
     * Executes the command with the provided task list, UI, and storage.
     *
     * @param tasks the task list to operate on
     * @param ui the UI used to display results and interact with the user
     * @param storage the storage used to save or load tasks
     * @throws NuggetException if an error occurs during the execution of the command
     */
    void execute(TaskList tasks, Ui ui, Storage storage) throws NuggetException;
}
