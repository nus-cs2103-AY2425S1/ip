package asura.commands;

import asura.data.exception.AsuraException;
import asura.data.tasks.TaskList;
import asura.storage.Storage;
import asura.ui.Ui;

/**
 * Represents a command done by the user
 */
public abstract class Command {
    StringBuilder output = new StringBuilder();

    /**
     * Executes the operations for the user specified command
     * @param taskList The list of tasks of the user
     * @param ui The UI object to give user feedback
     * @param storage The storage object to save/load tasks
     * @throws AsuraException If there is an error during the execution
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws AsuraException;

    /**
     * Checks whether the command is for the program to terminate
     * @return A boolean representing whether the program is to be terminated or not
     */
    public abstract boolean isExit();
}
