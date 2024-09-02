package rose.command;

import rose.Storage;
import rose.TaskList;
import rose.Ui;

import java.io.IOException;

/**
 * Represents a command used by user to unmark one of task in the list to become 'not done'.
 * <p>An <code>UnmarkCommand</code> object is represented by the index of the task in the list.
 */
public class UnmarkCommand extends Command {
    private int idx;

    public UnmarkCommand(int idx) {
        this.idx = idx;
    }

    /**
     * Unmarks the chosen task to become 'not done'.
     *
     * @param tasks current list of tasks.
     * @param ui ui object to show message to user.
     * @param storage storage object to store the data.
     * @throws IndexOutOfBoundsException If the user gives non-existing index.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.getTask(idx - 1).unmark();
            ui.showUnmark(tasks.getTask(idx - 1));
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
