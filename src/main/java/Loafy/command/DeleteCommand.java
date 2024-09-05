package loafy.command;

import loafy.loafyexception.LoafyException;
import loafy.tasklist.TaskList;
import loafy.ui.Ui;

/**
 * Represents a command to delete the task with the specified task id from a task list.
 */
public class DeleteCommand extends Command {
    private final int taskId;

    /**
     * Constructs a delete command for the specified task id.
     *
     * @param taskId Task id of the task to be deleted.
     */
    public DeleteCommand(int taskId) {
        this.taskId = taskId;
    }

    /**
     * Deletes the task with the task id in this command from the specified task list.
     * Prints a message to confirm the deletion of the task.
     * If the task id is invalid, an error message is shown to inform the user.
     *
     * @param taskList Task list from which the task will be deleted from.
     * @param ui User interface which will print the message.
     */
    public void execute(TaskList taskList, Ui ui) {
        if (taskList.isValid(this.taskId)) {
            ui.reply(taskList.delete(this.taskId));
        } else {
            ui.showError(LoafyException.ofInvalidAction());
        }
    }
}
