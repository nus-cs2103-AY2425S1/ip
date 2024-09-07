package nugget.command;

import nugget.*;

/**
 * Represents a command to add a task to the task list.
 * Implements the {@link Command} interface.
 */
public class AddTaskCommand implements Command {
    private Task task;

    /**
     * Creates a new {@code AddTaskCommand} with the specified task.
     *
     * @param task the task to be added
     */
    public AddTaskCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the command to add the task to the task list.
     * Saves the updated task list to storage and shows the result using the UI.
     *
     * @param tasks the task list to which the task will be added
     * @param ui the UI used to display the result
     * @param storage the storage used to save the updated task list
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(task);
        storage.saveTasks(tasks.getTasks());
        ui.showTaskAdded(task, tasks.size());
    }
}
