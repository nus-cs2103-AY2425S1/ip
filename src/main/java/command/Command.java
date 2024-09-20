package command;

import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Represents a command to be executed.
 */
public abstract class Command {
    /**
     * Executes the command.
     *
     * @param tasks   TaskList containing the tasks.
     * @param ui      Ui object to interact with the user.
     * @param storage Storage object to save the tasks.
     * @return        The response to the user.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * Returns true if the command is an exit command.
     *
     * @return true if the command is an exit command, false otherwise.
     */
    public boolean isExit() {
        return false;
    }
}
