package skibidi.command;

import skibidi.Storage;
import skibidi.TaskList;
import skibidi.TaskList.TaskNotFoundException;
import skibidi.Ui;
import skibidi.task.AbstractTask;

/** Command to delete an item from the task list. */
public class DeleteCommand extends AbstractCommand {
    private final int taskId;

    /** Construct new delete command using given task id. */
    public DeleteCommand(int taskId) {
        this.taskId = taskId;
    }

    /** Execute delete command and return string to be printed. */
    @Override
    public String execute(TaskList taskList, Storage storage, Ui ui) {
        try {
            AbstractTask task = taskList.deleteTask(taskId);
            return String.format("DELETED TASK: %s\n", task.toString())
                    + String.format("NUMBER OF TASKS IN LIST: %d", taskList.size());
        } catch (TaskNotFoundException err) {
            return err.getMessage();
        }
    }
}
