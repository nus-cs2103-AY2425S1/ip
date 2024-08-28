package action;

import exception.TaskIndexOutOfRangeException;
import task.Task;
import task.TaskList;

/**
 * Action to delete a task from the task list.
 *
 * @author dwsc37
 */
public class DeleteTaskAction extends Action {
    private final int taskIndex;

    /**
     * Constructor for a <code>DeleteTaskAction</code>.
     *
     * @param taskIndex Index for the task to be deleted.
     */
    public DeleteTaskAction(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Deletes a <code>task</code> from the <code>taskList</code> and returns a message representing the task deleted.
     *
     * @param taskList Task list to delete the task from.
     * @return Message representing the task deleted.
     */
    @Override
    public String execute(TaskList taskList) throws TaskIndexOutOfRangeException {
        if (taskIndex < 1 || taskIndex > taskList.getTaskCount()) {
            throw new TaskIndexOutOfRangeException(taskIndex, taskList.getSummary());
        }
        Task task = taskList.deleteTask(taskIndex - 1);
        return "Deleted task: " + task.toString();
    }
}
