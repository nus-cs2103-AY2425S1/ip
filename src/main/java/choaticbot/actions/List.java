package choaticbot.actions;

import choaticbot.tasks.TaskList;

public class List extends Action {

    public List(TaskList taskList) {
        super(taskList);
    }

    @Override
    public void execute() {
        this.taskList.listTask();
    }
}
