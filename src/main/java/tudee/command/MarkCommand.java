package tudee.command;

import tudee.task.TaskList;
import tudee.task.Task;
import tudee.ui.Ui;
import tudee.storage.Storage;

/**
 * Represents a command to mark a task as completed.
 * This command updates the status of a specific task to done,
 * and then updates the user interface and storage accordingly.
 */
public class MarkCommand extends Command {
    private final int index;

    /**
     * Constructs a MarkCommand with the specified index.
     *
     * @param index The index of the task to be marked as done.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command to mark the task at the specified index as completed.
     * Updates the user interface to show the task as marked and
     * saves the updated task list to storage.
     *
     * @param tasks The task list in which the task will be marked.
     * @param ui The user interface to update with the marked task.
     * @param storage The storage to save the updated task list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.get(index - 1);
        task.markAsDone();
        ui.showMark(task);
        storage.save(tasks.get());
    }
}