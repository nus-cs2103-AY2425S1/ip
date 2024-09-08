package bobbybot;

import bobbybot.tasks.Task;

/**
 * Represents a stub for a task for testing purposes.
 */
public class TaskStub extends Task {

    public static final String TASK_TYPE = "STUB";

    public TaskStub(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return String.format("[%s]%s", TASK_TYPE, super.toString());
    }

    @Override
    public String getFileString() {
        return TASK_TYPE + " | " + (isDone() ? "1" : "0") + " | " + getDescription();
    }
}
