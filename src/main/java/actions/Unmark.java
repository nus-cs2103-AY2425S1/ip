package actions;

import tasks.TaskList;

public class Unmark extends Action {
    public String details;

    public Unmark(TaskList taskList, String details) {
        super(taskList);
        this.details = details;
    }

    @Override
    public void execute() {
        int index = Integer.parseInt(this.details);
        this.taskList.unmarkTask(index);
    }
}

