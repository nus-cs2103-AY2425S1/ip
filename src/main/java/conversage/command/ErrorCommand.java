package conversage.command;

import conversage.exception.ConverSageException;
import conversage.storage.Storage;
import conversage.task.TaskList;
import conversage.ui.Ui;

/**
 * Represents a command to show an error message.
 */
public class ErrorCommand extends Command{
    private final String errorMsg;

    /**
     * Constructs an ErrorCommand with the specified error message.
     *
     * @param msg the error message to display.
     */
    public ErrorCommand(String msg) {
        this.errorMsg = msg;
    }


    /**
     * Executes the error command, displaying the error message in the UI.
     *
     * @param tasks   The task list (not used in this command).
     * @param ui      The UI to update.
     * @param storage The storage (not used in this command).
     * @return The error message.
     * @throws ConverSageException If an error occurs during execution.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws ConverSageException {
        ui.showLine();
        ui.showMessage(errorMsg);
        ui.showLine();
        return errorMsg;
    }
}
