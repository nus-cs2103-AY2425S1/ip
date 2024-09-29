package action;

import task.Task;
import task.TaskList;

/**
 * Action to add a task to the task list.
 *
 * @author dwsc37
 */
public class AddTaskAction extends Action {
    private final Task task;

    /**
     * Constructor for a <code>AddTaskAction</code>.
     *
     * @param task Task to be added.
     */
    public AddTaskAction(Task task) {
        this.task = task;
    }

    /**
     * Adds a <code>task</code> to the <code>taskList</code> and returns a message representing the task added.
     *
     * @param taskList Task list to add the task to.
     * @return Message representing the task added.
     */
    @Override
    public String execute(TaskList taskList) {
        taskList.addTask(task);
        return "Added task: " + task.toString() + "\n" + taskList.getSummary();
    }
}
