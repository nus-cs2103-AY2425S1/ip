package action;

import task.TaskList;

public class ListTasksAction extends Action {
    @Override
    public String execute(TaskList taskList) {
        return taskList.toString();
    }
}
