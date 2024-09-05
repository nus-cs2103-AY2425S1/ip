package fishman.command;

import fishman.task.Task;
import fishman.task.TaskList;
import fishman.utils.Ui;

/**
 * Represents the command to delete a task in the task list.
 * This command implements the Command interface and is for
 * deleting a single task in the task list and returns the confirmation message
 * that the task has been successfully deleted to the user.
 */
public class DeleteCommand implements Command {
    /** The index of the task in the task list to be deleted. */
    private final int index;

    /**
     * Constructs a DeleteCommand with the specified task to be deleted.
     * @param index The index of the task object in the task list to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * @inheritDoc
     *
     *      Deletes the task in the task list and returns a confirmation message alongside the
     *      current number of tasks in the list.
     *
     * @param tasks The Task List in which the task is deleted from.
     * @param ui The Ui instance used to generate the confirmation message.
     * @return The confirmation message indicating the command execution.
     */
    @Override
    public String execute(TaskList tasks, Ui ui) {
        Task tempTask = tasks.getTask(index);
        tasks.delete(index);
        return ui.getDeletedTaskMessage(tempTask, tasks.size());
    }
}
