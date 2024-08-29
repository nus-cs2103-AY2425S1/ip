package tudee.command;

import tudee.task.TaskList;
import tudee.task.Task;
import tudee.ui.Ui;
import tudee.storage.Storage;
import tudee.TudeeException;

/**
 * Represents a command to delete a task from the task list.
 * This command removes the task at a specified index and updates the
 * user interface and storage accordingly.
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Constructs a DeleteCommand with the specified index.
     *
     * @param index The index of the task to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command to delete the task at the specified index.
     * Updates the user interface to show the deleted task and
     * saves the updated task list to storage.
     *
     * @param tasks The task list from which the task will be deleted.
     * @param ui The user interface to update with the deleted task.
     * @param storage The storage to save the updated task list.
     * @throws TudeeException If the specified index is out of range.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws TudeeException {
        if (index > tasks.size() || index < 1) {
            throw new TudeeException("You do not have that many tasks!");
        }
        Task task = tasks.delete(index - 1);
        ui.showDelete(task, tasks.size());
        storage.save(tasks.get());
    }
}