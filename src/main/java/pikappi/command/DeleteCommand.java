package pikappi.command;

import pikappi.Storage;
import pikappi.TaskList;
import pikappi.Ui;
import pikappi.exception.PikappiException;

/**
 * Represents a command by user to delete a task.
 */
public class DeleteCommand extends Command {
    private int taskNum;

    /**
     * Creates a new DeleteCommand.
     *
     * @param taskNum The index of the task to be deleted.
     */
    public DeleteCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * Deletes the task from the list of tasks.
     *
     * @param tasks List of tasks.
     * @param ui Ui object that interacts with the user.
     * @param storage The storage object.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            return tasks.deleteTask(taskNum);
        } catch (PikappiException e) {
            return ui.showErrorMessage(e.getMessage());
        }
    }
}
