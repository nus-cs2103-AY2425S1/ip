package conversage.command;

import conversage.exception.ConverSageException;
import conversage.storage.Storage;
import conversage.task.TaskList;
import conversage.ui.Ui;

/**
 * Represents a command to exit the application.
 */
public class ByeCommand extends Command {

    /**
     * Executes the bye command, showing a goodbye message and indicating the application should exit.
     *
     * @param tasks   The task list (not used in this command).
     * @param ui      The UI to update.
     * @param storage The storage (not used in this command).
     * @return A message indicating the application is exiting.
     * @throws ConverSageException If an error occurs during execution.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws ConverSageException {
        ui.showLine();
        ui.showMessage("Goodbye. We shall meet again soon.");
        ui.showLine();
        return "Goodbye. We shall meet again soon.";
    }

    /**
     * Indicates that this command will cause the application to exit.
     *
     * @return true, as this command causes the application to exit.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
