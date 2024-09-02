package darkpool.command;

import darkpool.util.Storage;
import darkpool.util.TaskList;
import darkpool.util.Ui;
import darkpool.util.DarkpoolException;

/**
 * Represents a command to exit the Darkpool application.
 */
public class ExitCommand extends Command {

    /**
     * Executes the exit command, displaying a goodbye message and saving the task list.
     *
     * @param taskList The task list to save.
     * @param ui The UI to display the goodbye message.
     * @param storage The storage to save the task list.
     * @throws DarkpoolException If an error occurs during saving.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DarkpoolException {
        ui.goodbye();
        storage.saveData(taskList);
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