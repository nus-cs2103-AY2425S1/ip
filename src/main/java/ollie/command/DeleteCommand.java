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
    public void execute(TaskList tasks, Ui ui, Storage storage) throws OllieException {
        Task task = tasks.delete(index);
        ui.showRemoveTask(task, tasks.getSize());
    }
}

