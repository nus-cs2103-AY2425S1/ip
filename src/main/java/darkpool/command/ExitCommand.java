package darkpool.command;

import darkpool.gui.Gui;
import darkpool.util.DarkpoolException;
import darkpool.util.Storage;
import darkpool.util.TaskList;


/**
 * Represents a command to exit the Darkpool application.
 */
public class ExitCommand extends Command {

    /**
     * Executes the exit command, displaying a goodbye message and saving the task list.
     *
     * @param taskList The task list to save.
     * @param gui       The UI to display the goodbye message.
     * @param storage  The storage to save the task list.
     * @return
     * @throws DarkpoolException If an error occurs during saving.
     */
    @Override
    public String execute(TaskList taskList, Gui gui, Storage storage) throws DarkpoolException {
        storage.saveData(taskList);
        return gui.goodbye();
    }

    /**
     * Indicates that this command is an exit command.
     *
     * @return true, indicating this is an exit command.
     */
    @Override
    public boolean isExit() {
        return true;
    }

}
