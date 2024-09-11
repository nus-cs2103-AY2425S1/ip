package ollie.command;

import ollie.*;
import ollie.exception.OllieException;
import ollie.task.Task;

/**
 * Represents a command for removing a task from the task list.
 */
public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Execute the deletion of task.
     *
     * @param tasks   List of tasks.
     * @param ui      User interface controller.
     * @param storage Storage controller for file manipulation.
     */
    @Override
    public Response execute(TaskList tasks, Ui ui, Storage storage) throws OllieException {
        assert(index > -1 & index < tasks.getSize());
        Task task = tasks.delete(index);
        return new Response(ui.getRemoveTaskMessage(task, tasks.getSize()), false);
    }
}

