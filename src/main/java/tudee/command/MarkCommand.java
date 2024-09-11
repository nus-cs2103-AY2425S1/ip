package tudee.command;

import tudee.storage.Storage;
import tudee.task.Task;
import tudee.task.TaskList;
import tudee.ui.Ui;

/**
 * Represents a command to mark a task as completed.
 * This command updates the status of a specific task to be done.
 * Updates the user interface and storage accordingly.
 */
public class MarkCommand extends Command {
    private static final int INDEX_OFFSET = 1;
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
     * Updates the user interface to show the task as marked.
     * Saves the updated task list to storage.
     *
     * @param tasks The task list in which the task will be marked.
     * @param ui The user interface to update with the marked task.
     * @param storage The storage to save the updated task list.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        // Assert that tasks, ui and storage are not null.
        assert tasks != null : "TaskList cannot be null";
        assert ui != null : "Ui cannot be null";
        assert storage != null : "Storage cannot be null";

        Task task = tasks.getTask(index - INDEX_OFFSET);
        task.markAsDone();

        storage.save(tasks.getTasks());
        return ui.showMark(task);
    }
}
