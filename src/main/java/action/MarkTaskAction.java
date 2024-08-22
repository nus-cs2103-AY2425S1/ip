package action;

import task.Task;
import task.TaskList;

public class MarkTaskAction extends Action {
    private final int taskIndex;

    public MarkTaskAction(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public String execute(TaskList taskList) {
        Task task = taskList.markTask(taskIndex);
        return "Marked task as done: " + task.toString();
    }
}