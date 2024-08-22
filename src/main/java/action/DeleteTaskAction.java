package action;

import exception.TaskIndexOutOfRangeException;
import task.Task;
import task.TaskList;

public class DeleteTaskAction extends Action {
    private final int taskIndex;

    public DeleteTaskAction(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public String execute(TaskList taskList) throws TaskIndexOutOfRangeException {
        if (taskIndex < 1 || taskIndex > taskList.getTaskCount()) {
            throw new TaskIndexOutOfRangeException(taskIndex, taskList.getSummary());
        }
        Task task = taskList.deleteTask(taskIndex - 1);
        return "Deleted task: " + task.toString();
    }
}
