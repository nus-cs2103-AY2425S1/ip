package action;

import exception.TaskIndexOutOfRangeException;
import task.Task;
import task.TaskList;

/**
 * Action to mark a task as done.
 *
 * @author dwsc37
 */
public class MarkTaskAction extends Action {
    private final int taskIndex;

    /**
     * Constructor for a <code>MarkTaskAction</code>
     * @param taskIndex Index for the task to be marked as done.
     */
    public MarkTaskAction(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Marks a <code>task</code> from the <code>taskList</code> as done and returns a message representing the task.
     * @param taskList Task list with the task to mark as done.
     * @return Message representing the task marked as done.
     * @throws TaskIndexOutOfRangeException If <code>taskIndex</code> is out of range.
     */
    @Override
    public String execute(TaskList taskList) throws TaskIndexOutOfRangeException {
        if (taskIndex < 1 || taskIndex > taskList.getTaskCount()) {
            throw new TaskIndexOutOfRangeException(taskIndex, taskList.getSummary());
        }
        Task task = taskList.markTask(taskIndex - 1);
        return "Marked task as done: " + task.toString();
    }
}
