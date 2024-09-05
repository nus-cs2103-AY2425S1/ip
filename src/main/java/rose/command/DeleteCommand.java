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

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task deleted = tasks.getTask(idx - 1);
            tasks.deleteTask(idx - 1);
            ui.showDelete(deleted, tasks.size());
        } catch (IndexOutOfBoundsException e) {
            ui.showError("OOPS!!! Task index is out of bounds.");
        }

        try {
            storage.save(tasks.getTaskList());
        } catch (IOException e) {
            ui.showError("We cannot save the tasks: " + e.getMessage());
        }
    }
}
