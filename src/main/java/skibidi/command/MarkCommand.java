package skibidi.command;

import skibidi.Storage;
import skibidi.TaskList;
import skibidi.TaskList.TaskNotFoundException;
import skibidi.Ui;
import skibidi.task.AbstractTask;

/** Command to mark a task as done. */
public class MarkCommand extends AbstractCommand {
    private final int taskId;

    /** Construct new mark command instance for given task id. */
    public MarkCommand(int taskId) {
        this.taskId = taskId;
    }

    /** Execute mark command and return string to be printed. */
    @Override
    public String execute(TaskList taskList, Storage storage, Ui ui) {
        try {
            AbstractTask task = taskList.getTask(taskId);
            task.mark();
            String message = "MARKING TASK\n" + task.toString();
            return message;
        } catch (TaskNotFoundException err) {
            return err.getMessage();
        }
    }
}
