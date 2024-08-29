package actions;

import tasks.TaskList;

abstract public class Action {
    public TaskList taskList;

    public Action(TaskList taskList) {
        this.taskList = taskList;
    }

    abstract public void execute();

    public boolean isEnd() {
        return false;
    };
}
