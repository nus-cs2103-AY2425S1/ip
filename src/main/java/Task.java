public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    void markAsDone() {
        this.isDone = true;
    }

    void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task.
     * Includes the status icon and the task description.
     *
     * @return Task as a formatted string.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
