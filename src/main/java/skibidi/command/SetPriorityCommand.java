package skibidi.command;

import skibidi.Storage;
import skibidi.TaskList;
import skibidi.TaskList.TaskNotFoundException;
import skibidi.Ui;
import skibidi.task.AbstractTask;

/** Command to list all items in current task list. */
public class SetPriorityCommand extends AbstractCommand {
    private final int taskId;
    private final int priority;

    /** Construct new mark command instance for given task id. */
    public SetPriorityCommand(int taskId, int priority) {
        this.taskId = taskId;
        this.priority = priority;
    }

    /** Execute list command and return string representing items to be printed. */
    @Override
    public String execute(TaskList taskList, Storage storage, Ui ui) {
        try {
            AbstractTask task = taskList.getTask(taskId);
            task.setPriority(priority);
            String message = String.format("SET PRIORITY OF TASK %d TO %d\n", taskId, priority);
            return message;
        } catch (TaskNotFoundException e) {
            return e.getMessage();
        }
    }
}
