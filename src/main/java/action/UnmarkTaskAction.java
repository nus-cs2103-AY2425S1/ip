package action;

import exception.BotException;
import exception.InvalidTaskIndexException;
import exception.TaskIndexOutOfRangeException;
import task.Task;
import task.TaskList;

/**
 * Action to mark a task as undone
 *
 * @author dwsc37
 */
public class UnmarkTaskAction extends Action {
    private final int taskIndex;

    public UnmarkTaskAction(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public String execute(TaskList taskList) throws TaskIndexOutOfRangeException {
        if (taskIndex < 1 || taskIndex > taskList.getTaskCount()) {
            throw new TaskIndexOutOfRangeException(taskIndex, taskList.getSummary());
        }
        Task task = taskList.unmarkTask(taskIndex - 1);
        return "Marked task as undone: " + task.toString();
    }
}
