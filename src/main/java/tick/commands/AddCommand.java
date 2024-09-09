package tick.commands;

import tick.exceptions.TickException;
import tick.storage.Storage;
import tick.storage.TaskList;
import tick.tasks.Task;
import tick.ui.Ui;

/**
 * Represents a command to add a task to the task list.
 */
public class AddCommand extends Command {
    private Task toAdd;

    /**
     * Constructs an AddCommand with a given task.
     *
     * @param task The task to be added.
     */
    public AddCommand(Task task) {
        this.toAdd = task;
    }

    /**
     * Add a task to the task list and update the storage file.
     *
     * @param tasks The task list where task is added to.
     * @param ui The user interface to display information to the user.
     * @param storage The storage file to be updated.
     * @throws TickException If there is an error adding the task.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws TickException {
        tasks.addTask(this.toAdd);
        ui.showTaskAdded(this.toAdd, tasks.getSize());
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
