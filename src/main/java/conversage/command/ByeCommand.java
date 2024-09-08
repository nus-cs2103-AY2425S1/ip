package conversage.command;

import conversage.exception.ConverSageException;
import conversage.storage.Storage;
import conversage.task.TaskList;
import conversage.ui.Ui;

/**
 * Represents a command to exit the application.
 */
public class ByeCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws ConverSageException {
        ui.showLine();
        ui.showMessage("Goodbye. We shall meet again soon.");
        ui.showLine();
        return "Goodbye. We shall meet again soon.";
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
