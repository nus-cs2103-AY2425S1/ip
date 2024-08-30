package tudee.command;

import tudee.task.TaskList;
import tudee.ui.Ui;
import tudee.storage.Storage;
import tudee.task.Task;

/**
 * Represents a command to mark a task as not completed.
 * This command updates the status of a specific task to not done,
 * and then updates the user interface and storage accordingly.
 */
public class UnmarkCommand extends Command {
    private int index;

    /**
     * Constructs an UnmarkCommand with the specified index.
     *
     * @param index The index of the task to be marked as not done.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command to mark the task at the specified index as not completed.
     * Updates the user interface to show the task as unmarked and
     * saves the updated task list to storage.
     *
     * @param tasks The task list in which the task will be unmarked.
     * @param ui The user interface to update with the unmarked task.
     * @param storage The storage to save the updated task list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.get(index - 1);
        task.markAsNotDone();
        ui.showUnMark(task);
        storage.save(tasks.get());
    }
}