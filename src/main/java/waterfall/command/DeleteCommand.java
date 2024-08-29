package waterfall.command;

import java.io.IOException;

import waterfall.Storage;
import waterfall.Ui;
import waterfall.task.*;

/**
 * Represents the <code>Command</code> object to delete a <code>Task</code>
 * from the data storage and alerts the user.
 *
 * @author Wai Hong
 */

public class DeleteCommand extends Command {
    final int index;

    /**
     * Constructs a command to delete task at specified index.
     * @param index
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Task task = tasks.getTask(index);
        tasks.delete(index);
        storage.updateTask(tasks.getTasks());
        ui.showDeleteMessage(task);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
