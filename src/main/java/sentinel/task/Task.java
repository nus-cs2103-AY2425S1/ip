package sentinel.task;

/**
 * Represents a task with a description and a status indicating whether it is done.
 */
public class Task {
    /**
     * The description of the task.
     */
    private final String description;

    /**
     * The status of the task; true if the task is done, false otherwise.
     */
    private boolean isDone;

    /**
     * Constructs a new sentinel.task.Task with the specified description.
     * The task is initially marked as not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task.
     * "X" indicates that the task is done, and a space (" ") indicates that it is not done.
     *
     * @return The status icon of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the status of the task
     *
     * @return The status of the task.
     */
    public boolean getStatus() {
        return isDone;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task, including its status and description.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }
}
