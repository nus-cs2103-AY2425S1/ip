package action;

import task.TaskList;

/**
 * Action to list all tasks.
 *
 * @author dwsc37
 */
public class ListTasksAction extends Action {
    /**
     * Returns a list of all tasks in a <code>taskList</code>.
     * @param taskList The task list to be displayed.
     * @return List of tasks.
     */
    @Override
    public String execute(TaskList taskList) {
        return taskList.toString();
    }
}
