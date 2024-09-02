package rose.command;

import rose.RoseException;
import rose.Storage;
import rose.TaskList;
import rose.Ui;

import java.io.IOException;

/**
 * Represents a command used by user to mark one of task in the list as 'Done'.
 * <p>A <code>MarkCommand</code> object is represented by the index of the task in the list.
 */
public class MarkCommand extends Command {
    private int idx;

    public MarkCommand(int idx) {
        this.idx = idx;
    }

    /**
     * Marks the chosen task as 'done'.
     *
     * @param tasks current list of tasks.
     * @param ui ui object to show message to user.
     * @param storage storage object to store the data.
     * @throws IndexOutOfBoundsException If the user gives non-existing index.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.getTask(idx - 1).mark();
            ui.showMark(tasks.getTask(idx - 1));
            //ui.display("Marked as done : ");
            //ui.display(tasks.getTask(idx - 1).toString());
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
