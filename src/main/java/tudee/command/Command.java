package tudee.command;

import tudee.TudeeException;
import tudee.storage.Storage;
import tudee.task.TaskList;
import tudee.ui.Ui;

/**
 * Represents an abstract command in the application.
 * Commands that extend this class define specific actions to be performed when executed.
 */
public abstract class Command {
    /**
     * Executes the command with the given task list, user interface, and storage.
     *
     * @param tasks The task list to be modified by the command.
     * @param ui The user interface to be updated by the command.
     * @param storage The storage to be updated by the command.
     * @throws TudeeException If an error occurs during execution.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws TudeeException;

    /**
     * This method checks that none of the provided parameters are null.
     *
     * @param tasks The task list to be validated.
     * @param ui The user interface to be validated.
     * @param storage The storage object to be validated.
     */
    protected void validateInputs(TaskList tasks, Ui ui, Storage storage) {
        assert tasks != null : "TaskList cannot be null";
        assert ui != null : "Ui cannot be null";
        assert storage != null : "Storage cannot be null";
    }
}
