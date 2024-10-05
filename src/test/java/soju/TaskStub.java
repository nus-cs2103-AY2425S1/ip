package soju;

import soju.tasks.Task;

/**
 * TaskStub is a stub for task class
 */
public class TaskStub extends Task {

    public TaskStub(String description) {
        super(description);
    }

    @Override
    public String toFileString() {
        return null;
    }
}
