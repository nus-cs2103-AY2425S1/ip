package darkpool.command;

import darkpool.util.DarkpoolException;
import darkpool.util.Storage;
import darkpool.util.TaskList;
import darkpool.gui.Gui;


/**
 * Represents an abstract command that can be executed.
 */
public abstract class Command {

    /**
     * Executes the command with the given task list, UI, and storage.
     *
     * @param taskList The task list to operate on.
     * @param gui       The UI to interact with the user.
     * @param storage  The storage to save or load data.
     * @return
     * @throws DarkpoolException If an error occurs during command execution.
     */
    public abstract String execute(TaskList taskList, Gui gui, Storage storage) throws DarkpoolException;

    /**
     * Indicates whether the command is an exit command.
     *
     * @return false by default, can be overridden by subclasses.
     */
    public boolean isExit() {
        return false;
    }

}
