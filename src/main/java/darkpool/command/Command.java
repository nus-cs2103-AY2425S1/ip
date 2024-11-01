package darkpool.command;

import darkpool.DarkpoolException;
import darkpool.gui.Gui;
import darkpool.storage.Storage;
import darkpool.tasklist.TaskList;

/**
 * Represents a command that can be executed by the user.
 */
public abstract class Command {

    /**
     * Executes the command.
     *
     * @param taskList The task list to be modified.
     * @param gui The GUI to display messages.
     * @param storage The storage to save the task list.
     * @return The response to be displayed to the user.
     * @throws DarkpoolException If an error occurs during execution.
     */
    public abstract String execute(TaskList taskList, Gui gui, Storage storage) throws DarkpoolException;

    /**
     * Returns true if the command is an exit command.
     *
     * @return True if the command is an exit command.
     */
    public boolean isExit() {
        return false;
    }

}
