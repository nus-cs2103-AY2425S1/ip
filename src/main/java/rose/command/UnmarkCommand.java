package rose.command;

import java.io.IOException;

import rose.Storage;
import rose.TaskList;
import rose.Ui;

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
     * @return A message indicating the result of the operation, such as the unmarked task or an error message.
     * @throws IndexOutOfBoundsException If the user gives non-existing index.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.getTask(idx - 1).unmark();
            try {
                storage.save(tasks.getTaskList());
            } catch (IOException e) {
                ui.showError("We cannot save the tasks: " + e.getMessage());
            }
            return ui.showUnmark(tasks.getTask(idx - 1));
        } catch (IndexOutOfBoundsException e) {
            return ui.showError("OOPS!!! Task index is out of bounds.");
        }
    }
}
