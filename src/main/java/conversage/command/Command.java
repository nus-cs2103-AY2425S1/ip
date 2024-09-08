package conversage.command;

import conversage.exception.ConverSageException;
import conversage.storage.Storage;
import conversage.task.TaskList;
import conversage.ui.Ui;
import javafx.beans.binding.StringBinding;

/**
 * Represents a command that can be executed.
 */
public abstract class Command {

    /**
     * Executes the command with the given task list, UI, and storage.
     *
     * @param tasks   the task list.
     * @param ui      the user interface.
     * @param storage the storage.
     * @throws ConverSageException if an error occurs during execution.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws ConverSageException;

    /**
     * Returns true if the command is an exit command.
     *
     * @return true if the command is an exit command, false otherwise.
     */
    public boolean isExit() {
        return false;
    }
}
