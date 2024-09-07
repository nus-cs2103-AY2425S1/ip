package nugget.command;

import nugget.*;
import nugget.exception.InvalidTaskNumberException;
import nugget.exception.NuggetException;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteTaskCommand implements Command {
    private int index;

    /**
     * Constructs a <code>DeleteTaskCommand</code> with the specified task index to be deleted.
     *
     * @param index The index of the task to be deleted.
     */
    public DeleteTaskCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the delete task command. Deletes the task at the specified index from the task list,
     * saves the updated task list to storage, and displays the task removal to the user.
     *
     * @param tasks The task list containing the task to be deleted.
     * @param ui The user interface used to display the task removal.
     * @param storage The storage handler to save the updated task list.
     * @throws NuggetException if the specified task index is invalid.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NuggetException {
        if (index < 0 || index >= tasks.size()) {
            throw new InvalidTaskNumberException();
        }
        Task task = tasks.getTask(index);
        tasks.deleteTask(index);
        storage.saveTasks(tasks.getTasks());
        ui.showTaskRemoved(task, tasks.size());
    }
}
