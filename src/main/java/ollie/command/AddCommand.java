package ollie.command;

import ollie.Storage;
import ollie.task.Task;
import ollie.TaskList;
import ollie.Ui;

/**
 * Represents a command for adding to task to the task list.
 */
public class AddCommand extends Command {
    Task task;
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Execute the addition of task.
     *
     * @param tasks List of tasks.
     * @param ui User interface controller.
     * @param storage Storage controller for file manipulation.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(this.task);
        ui.showAddTask(task, tasks.getSize());
    }
}
