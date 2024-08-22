package action;

import task.TaskList;

/**
 * Action to list all tasks
 *
 * @author dwsc37
 */
public class ListTasksAction extends Action {
    @Override
    public String execute(TaskList taskList) {
        return taskList.toString();
    }
}
