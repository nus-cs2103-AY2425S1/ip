package Mentos.components;

import Mentos.task.Task;

public class ActionTaskIndexTuple {
    private final String action;
    private final Task task;
    private final int index;

    public ActionTaskIndexTuple(String string, Task task, int index) {
        this.task = task;
        this.action = string;
        this.index = index;
    }
    public String getAction() {
        return action;
    }

    public Task getTask() {
        return task;
    }
    public Integer getIndex(){
        return this.index;
    }

}
