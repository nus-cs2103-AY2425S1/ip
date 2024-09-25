package ollie.command;

import ollie.History;
import ollie.Response;
import ollie.Storage;
import ollie.TaskList;
import ollie.Ui;
import ollie.task.Task;
/**
 * Represents a command for adding to task to the task list.
 */
public class AddCommand extends Command {
    private Task task;

    /**
     * Constructs a command object for adding of task.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the addition of task to list.
     *
     * @param tasks   List of tasks.
     * @param ui      User interface controller.
     * @param storage Storage controller for file manipulation.
     */
    @Override
    public Response execute(TaskList tasks, Ui ui, Storage storage, History history) {
        tasks.add(this.task);
        history.add(new DeleteCommand(task));
        return new Response(ui.getAddTaskMessage(task, tasks.getSize()), false);
    }
}
