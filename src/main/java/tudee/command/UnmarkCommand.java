package tudee.command;

import tudee.storage.Storage;
import tudee.task.Task;
import tudee.task.TaskList;
import tudee.ui.Ui;

/**
 * Represents a command to mark a task as not completed.
 * This command updates the status of a specific task to not done.
 * Updates the user interface and storage accordingly.
 */
public class UnmarkCommand extends Command {
    private static final int INDEX_OFFSET = 1;
    private final int index;

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
     * Updates the user interface to show the task as unmarked.
     * Saves the updated task list to storage.
     *
     * @param tasks The task list in which the task will be unmarked.
     * @param ui The user interface to update with the unmarked task.
     * @param storage The storage to save the updated task list.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.getTask(index - INDEX_OFFSET);
        task.markAsNotDone();

        storage.save(tasks.getTasks());
        return ui.showUnmark(task);
    }
}
