package waterfall.command;

import java.io.IOException;

import waterfall.Storage;
import waterfall.Ui;
import waterfall.task.Task;
import waterfall.task.TaskList;

/**
 * Represents the <code>Command</code> object to delete a <code>Task</code>
 * from the data storage and alerts the user.
 *
 * @author Wai Hong
 */

public class DeleteCommand extends UndoableCommand {
    private final int index;
    private Task task;

    /**
     * Constructs a command to delete task at specified index.
     * @param index index of the task to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        task = tasks.getTask(index);
        tasks.delete(index);
        storage.updateTask(tasks.getTasks());
        addToUndoList(this);
        return ui.showDeleteMessage(task);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void undo(TaskList tasks, Storage storage) throws IOException {
        tasks.add(index, task);
        storage.updateTask(tasks.getTasks());
    }
}
