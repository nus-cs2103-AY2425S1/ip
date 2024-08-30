package lolo.command;

import lolo.Ui;
import lolo.LoloException;
import lolo.storage.Storage;
import lolo.task.Task;
import lolo.task.TaskList;

/**
 * Represents a command to add a task to the task list.
 * This command interacts with the user interface to confirm the addition
 * and updates the storage with the new task list.
 */
class AddCommand extends Command {
    private Task task;

    /**
     * Constructs an AddCommand with the specified task.
     *
     * @param task The task to be added to the task list.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the command by adding the task to the task list,
     * displaying a confirmation message to the user, and saving
     * the updated task list to storage.
     *
     * @param tasks The list of tasks to be modified by the command.
     * @param ui The user interface to interact with the user.
     * @param storage The storage to save the updated task list.
     * @throws LoloException If there is an error during execution or saving.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws LoloException {
        tasks.addTask(task);
        ui.showAddedTask(task, tasks.size());
        storage.save(tasks.getTasks());
    }
}

