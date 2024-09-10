package loafy.command;

import loafy.loafyexception.LoafyException;
import loafy.tasklist.TaskList;
import loafy.ui.Ui;

/**
 * Represents a command to mark the task with the specified task id as done or undone.
 */
public class MarkCommand extends Command {
    private final boolean isDone;
    private final int taskId;

    /**
     * Constructs a mark command for the specified task id.
     *
     * @param isDone {@code true} if the task is to be marked as done and {@code false} if undone.
     * @param taskId The task id of the task to be marked.
     */
    public MarkCommand(boolean isDone, int taskId) {
        this.isDone = isDone;
        this.taskId = taskId;
    }

    /**
     * Marks the task with the task id as done or undone in the task list.
     * Prints a message to confirm marking of the task.
     * If the task id is invalid, an error message is shown to inform the user.
     *
     * @param tasks Task list on which the task will be marked.
     * @param ui User interface which will print the message.
     */
    public String execute(TaskList tasks, Ui ui) {
        if (tasks.isValid(this.taskId)) {
            return tasks.markTask(this.isDone, this.taskId);
        } else {
            return ui.showError(LoafyException.ofInvalidAction());
        }
    }
}
