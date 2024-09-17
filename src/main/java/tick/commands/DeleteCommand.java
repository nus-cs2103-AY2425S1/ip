package tick.commands;

import tick.exceptions.TickException;
import tick.storage.Storage;
import tick.storage.TaskList;
import tick.tasks.Task;
import tick.ui.Ui;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    private int toDelete;

    /**
     * Constructs an DeleteCommand with a given index.
     *
     * @param index Index of the task to be deleted.
     */
    public DeleteCommand(int index) {
        this.toDelete = index;
    }

    /**
     * Deletes a task from the task list and updates the storage file.
     *
     * @param tasks TaskList where task is deleted from.
     * @param ui The user interface to display information to the user.
     * @param storage The storage file to be updated.
     * @throws TickException If there is an error deleting the task.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws TickException {
        assert tasks != null : "TaskList cannot be null.";
        Task deletedTask = tasks.deleteTask(this.toDelete);
        ui.showTaskDeleted(deletedTask, tasks.getSize());
        storage.saveData(tasks.getAllTasks());
    }

    /**
     * Returns false as the command is not an exit command.
     *
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
