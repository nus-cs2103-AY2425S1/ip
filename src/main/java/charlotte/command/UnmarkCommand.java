package charlotte.command;

import charlotte.exception.CharlotteException;
import charlotte.storage.Storage;
import charlotte.task.Task;
import charlotte.task.TaskList;
import charlotte.ui.Ui;

/**
 * Represents a command to unmark a task as not done.
 */
public class UnmarkCommand extends Command {
    private final int index;

    /**
     * Constructs an UnmarkCommand with the specified index.
     *
     * @param index The index of the task in the task list to be unmarked.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command to unmark a task as not done.
     * <p>
     * This method checks if the provided index is valid (i.e., within the range of the task list). If it is
     * valid, the task at the specified index is unmarked as not done, and the updated task list is saved
     * to storage. The user is informed of the successful update via the UI. If the index is invalid, a
     * CharlotteException is thrown.
     * </p>
     *
     * @param tasks The TaskList object containing all the tasks, including the one to be unmarked.
     * @param ui The Ui object used to display messages to the user.
     * @param storage The Storage object used to save the updated task list.
     * @throws CharlotteException If the provided index is invalid.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws CharlotteException {
        assert tasks != null : "TaskList should not be null";
        assert ui != null : "Ui should not be null";
        assert storage != null : "Storage should not be null";

        if (index < 1 || index > tasks.getSize()) {
            throw new CharlotteException("Task number is invalid. Please try again");
        }

        Task task = tasks.getTask(index - 1);

        assert task != null : "Task at the given index should not be null";
        task.unmark();
        storage.saveTasks(tasks);
        return ui.showMessage("OK, I've marked this task as not done yet:\n  " + task);
    }
}
