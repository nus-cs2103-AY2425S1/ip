package tudee.command;

import tudee.storage.Storage;
import tudee.task.Task;
import tudee.task.TaskList;
import tudee.ui.Ui;

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
     * Updates the user interface to show the added task.
     * Saves the updated task list to storage.
     *
     * @param tasks The task list to which the task will be added.
     * @param ui The user interface to update with the added task.
     * @param storage The storage to save the updated task list.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        // Assert that tasks, ui and storage are not null.
        assert tasks != null : "TaskList cannot be null";
        assert ui != null : "Ui cannot be null";
        assert storage != null : "Storage cannot be null";

        tasks.addTask(task);
        storage.save(tasks.getTasks());
        return ui.showAdd(task, tasks.numOfTasks());
    }
}
