package tudee.command;

import tudee.task.TaskList;
import tudee.task.Task;
import tudee.ui.Ui;
import tudee.storage.Storage;

/**
 * Represents a command to add a task to the task list.
 * This command updates the task list, user interface, and storage.
 */
public class AddTaskCommand extends Command {
    private final Task task;

    /**
     * Constructs an AddTaskCommand with the specified task.
     *
     * @param task The task to be added to the task list.
     */
    public AddTaskCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the command to add the task to the task list.
     * Updates the user interface to show the added task and
     * saves the updated task list to storage.
     *
     * @param tasks The task list to which the task will be added.
     * @param ui The user interface to update with the added task.
     * @param storage The storage to save the updated task list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(task);
        ui.showAdd(task, tasks.size());
        storage.save(tasks.get());
    }
}