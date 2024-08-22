package action;

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
    public String execute(TaskList taskList) {
        Task task = taskList.unmarkTask(taskIndex);
        return "Marked task as undone: " + task.toString();
    }
}
