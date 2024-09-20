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

    public ErrorCommand(String msg) {
        this.errorMsg = msg;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws ConverSageException {
        ui.showLine();
        ui.showMessage(errorMsg);
        ui.showLine();
        return errorMsg;
    }
}
