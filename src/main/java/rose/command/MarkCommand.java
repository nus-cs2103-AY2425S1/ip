package rose.command;

import java.io.IOException;

import rose.Storage;
import rose.TaskList;
import rose.Ui;

/**
 * Represents a command used by user to mark one of task in the list as 'Done'.
 * <p>A <code>MarkCommand</code> object is represented by the index of the task in the list.
 */
public class MarkCommand extends Command {
    private final int idx;

    public MarkCommand(int idx) {
        this.idx = idx;
    }

    /**
     * Marks the chosen task as 'done'.
     *
     * @param tasks current list of tasks.
     * @param ui ui object to show message to user.
     * @param storage storage object to store the data.
     * @return A message indicating the result of the operation, such as the marked task or an error message.
     * @throws IndexOutOfBoundsException If the user gives non-existing index.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.getTask(idx - 1).mark();
            try {
                storage.save(tasks.getTaskList());
            } catch (IOException e) {
                ui.showError("We cannot save the tasks: " + e.getMessage());
            }
            return ui.showMark(tasks.getTask(idx - 1));
        } catch (IndexOutOfBoundsException e) {
            return ui.showError("OOPS!!! Task index is out of bounds.");
        }


    }
}
