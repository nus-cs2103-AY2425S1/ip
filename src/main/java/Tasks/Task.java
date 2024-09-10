package tasks;

public abstract class Task {
    protected String name;
    protected Boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * Toggles isDone as true.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Toggles isDone as false.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns string representation of the isDone status.
     */
    public String getTaskStatus() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns generated write format for the specific task.
     */
    public abstract String getWriteTaskInfo();

    @Override
    public String toString() {
        return String.format("[%s] %s", getTaskStatus(), this.name);
    }
}
