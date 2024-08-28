package action;

import exception.TaskIndexOutOfRangeException;
import task.Task;
import task.TaskList;

/**
 * Action to mark a task as undone.
 *
 * @author dwsc37
 */
public class UnmarkTaskAction extends Action {
    private final int taskIndex;

    /**
     * Constructor for an <code>UnmarkTaskAction</code>
     * @param taskIndex Index for the task to be marked as undone.
     */
    public UnmarkTaskAction(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Marks a <code>task</code> from the <code>taskList</code> as undone and returns a message representing the task.
     * @param taskList Task list with the task to mark as undone.
     * @return Message representing the task marked as undone.
     * @throws TaskIndexOutOfRangeException If <code>taskIndex</code> is out of range.
     */
    @Override
    public String execute(TaskList taskList) throws TaskIndexOutOfRangeException {
        if (taskIndex < 1 || taskIndex > taskList.getTaskCount()) {
            throw new TaskIndexOutOfRangeException(taskIndex, taskList.getSummary());
        }
        Task task = taskList.unmarkTask(taskIndex - 1);
        return "Marked task as undone: " + task.toString();
    }
}
