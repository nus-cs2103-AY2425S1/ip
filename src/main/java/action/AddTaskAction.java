package action;

import task.Task;
import task.TaskList;

public class AddTaskAction extends Action {
    private final Task task;

    public AddTaskAction(Task task) {
        this.task = task;
    }

    @Override
    public String execute(TaskList taskList) {
        taskList.addTask(task);
        return "Added task: " + task.toString() + "\n" + taskList.getTaskSummary();
    }
}
