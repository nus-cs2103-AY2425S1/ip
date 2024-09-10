package rose.command;

import java.io.IOException;

import rose.Storage;
import rose.TaskList;
import rose.Ui;
import rose.task.Task;

/**
 * Represents a command used by user to delete task from the list.
 * <p>A <code>Delete</code> object is represented by the index of the task in the list.
 */
public class DeleteCommand extends Command {
    private int idx;

    public DeleteCommand(int idx) {
        this.idx = idx;
    }

    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task deleted = tasks.getTask(idx - 1);
            tasks.deleteTask(idx - 1);
            try {
                storage.save(tasks.getTaskList());
            } catch (IOException e) {
                return ui.showError("We cannot save the tasks: " + e.getMessage());
            }
            return ui.showDelete(deleted, tasks.size());
        } catch (IndexOutOfBoundsException e) {
            return ui.showError("OOPS!!! Task index is out of bounds.");
        }
    }
}
