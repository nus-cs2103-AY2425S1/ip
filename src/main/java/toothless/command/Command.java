package toothless.command;

import toothless.exceptions.ToothlessExceptions;
import toothless.storage.Storage;
import toothless.task.TaskList;
import toothless.ui.Ui;

/**
 * Represents a command to be executed by the program.
 */
public abstract class Command {
    /**
     * Executes the command.
     *
     * @param tasks   the list of tasks
     * @param ui      the user interface
     * @param storage the storage object
     * @throws ToothlessExceptions if an error occurs during execution
     */
    public abstract String executeCommand(TaskList tasks, Ui ui, Storage storage) throws ToothlessExceptions;
}
