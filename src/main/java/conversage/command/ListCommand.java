package conversage.command;

import conversage.exception.ConverSageException;
import conversage.storage.Storage;
import conversage.task.TaskList;
import conversage.ui.Ui;

/**
 * Constructs a DeleteCommand with the specified task index to delete.
 *
 * @param toDel the index of the task to be deleted.
 */
public class ListCommand extends Command {
    public ListCommand() {
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ConverSageException {
        ui.showLine();
        for (int i = 1; i <= tasks.size(); i++) {
            ui.showMessage(i + ". " + tasks.getTask(i - 1).toString());
        }
        ui.showLine();
    }
}