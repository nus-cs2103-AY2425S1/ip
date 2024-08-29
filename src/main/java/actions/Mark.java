package actions;

import tasks.TaskList;

public class Mark extends Action {
    public String details;

    public Mark(TaskList taskList, String details) {
        super(taskList);
        this.details = details;
    }

    @Override
    public void execute() {
        int index = Integer.parseInt(this.details);
        this.taskList.markTask(index);
    }
}

