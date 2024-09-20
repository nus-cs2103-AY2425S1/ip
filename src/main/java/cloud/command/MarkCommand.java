package cloud.command;

import cloud.util.Storage;
import cloud.util.TaskList;
import cloud.util.Ui;

/**
 * Represents a command to mark a task as done.
 * A <code>MarkCommand</code> object takes the task index and marks it as completed in the task list.
 */
public class MarkCommand extends Command {
    private final int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.mark(index);
        storage.saveData(tasks);
        return ui.showMarked(tasks.getTaskStatus(index));
    }
}
