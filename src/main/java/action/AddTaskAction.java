package action;

import task.Task;
import task.TaskList;

/**
 * Action to add a task to the task list
 *
 * @author dwsc37
 */
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
