package command;

import exception.DynamikeException;
import storage.Storage;
import storage.TaskList;
import task.Task;
import ui.Ui;

/**
 * Represents a command to add a task to the task list.
 */
public class AddCommand extends Command {
    private Task task;

    /**
     * Creates an AddCommand with the given task.
     *
     * @param task The task to be added.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Adds the task to the task list and updates the storage file.
     *
     * @param tasks The task list to which the task is to be added.
     * @param ui The user interface to interact with the user.
     * @param storage The storage file to be updated.
     * @throws DynamikeException If there is an error adding the task.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DynamikeException {
        assert task != null : "Task should not be null";
        assert tasks != null : "Task list should not be null";
        tasks.addTask(task);
        assert tasks.getTask(tasks.getSize() - 1).equals(task) : "Task should be added to the task list";
        storage.saveTasks(tasks);
        ui.showAdded(task, tasks);
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
