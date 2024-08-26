package soju;

import soju.tasks.Task;

public class TaskStub extends Task {

    public TaskStub(String description) {
        super(description);
    }

    @Override
    public String toFileString() {
        return null;
    }
}
