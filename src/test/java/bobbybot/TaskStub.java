package bobbybot;

import bobbybot.tasks.Task;

/**
 * Represents a stub for a task for testing purposes.
 */
public class TaskStub extends Task {

    public TaskStub(String description) {
        super(description);
    }

    @Override
    public String getTaskType() {
        return "T";
    }

    @Override
    public String toString() {
        return String.format("[%s]%s", getTaskType(), super.toString());
    }

    @Override
    public String getFileString() {
        return getTaskType() + " | " + (isDone() ? "1" : "0") + " | " + getDescription();
    }
}
