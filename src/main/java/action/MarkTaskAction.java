package action;

import exception.BotException;
import exception.InvalidTaskIndexException;
import exception.TaskIndexOutOfRangeException;
import task.Task;
import task.TaskList;

/**
 * Action to mark a task as done
 *
 * @author dwsc37
 */
public class MarkTaskAction extends Action {
    private final int taskIndex;

    public MarkTaskAction(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public String execute(TaskList taskList) throws TaskIndexOutOfRangeException {
        if (taskIndex < 1 || taskIndex > taskList.getTaskCount()) {
            throw new TaskIndexOutOfRangeException(taskIndex, taskList.getSummary());
        }
        Task task = taskList.markTask(taskIndex - 1);
        return "Marked task as done: " + task.toString();
    }
}