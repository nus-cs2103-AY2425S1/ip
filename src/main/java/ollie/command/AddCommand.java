package ollie.command;

import ollie.Response;
import ollie.Storage;
import ollie.task.Task;
import ollie.TaskList;
import ollie.Ui;

/**
 * Represents a command for adding to task to the task list.
 */
public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Execute the addition of task.
     *
     * @param tasks   List of tasks.
     * @param ui      User interface controller.
     * @param storage Storage controller for file manipulation.
     */
    @Override
    public Response execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(this.task);
        return new Response(ui.getAddTaskMessage(task, tasks.getSize()), false);
    }
}
