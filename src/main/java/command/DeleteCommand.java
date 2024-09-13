package command;

import exception.DynamikeException;
import storage.Storage;
import storage.TaskList;
import task.Task;
import ui.Ui;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Creates a DeleteCommand with the given index.
     *
     * @param index The index of the task to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Deletes the task from the task list and updates the storage file.
     *
     * @param tasks The task list from which the task is to be deleted.
     * @param ui The user interface to interact with the user.
     * @param storage The storage file to be updated.
     * @throws DynamikeException If there is an error deleting the task.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DynamikeException {
        assert tasks != null : "Task list should not be null";
        Task task = tasks.deleteTask(index);
        storage.saveTasks(tasks);
        ui.showDeleted(task, tasks);
    }

    /**
     * Returns false because this is not an exit command.
     *
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
