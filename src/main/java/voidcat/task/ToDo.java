package voidcat.task;

import voidcat.task.Task;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    public ToDo(String description, int done) {
        super(description);
        if (done == 1) {
            this.isDone = true;
        }
    }
    @Override
    public String getTaskType() {
        return "T";
    }

}
