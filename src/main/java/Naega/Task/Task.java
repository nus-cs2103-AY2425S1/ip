package Naega.Task;

/**
 * Represents a generic task with a description and a completion status.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new Task with the specified description.
     * Initializes the task as not done.
     *
     * @param description the description of the task
     */
    public Task(String description) {
        assert description != null && !description.isEmpty() : "Task description must not be null or empty";
        this.description = description;
        this.isDone = false;
    }

    /**
     * Checks if the task is marked as done.
     *
     * @return true if the task is done, false otherwise
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        assert !isDone : "Task should not already be marked as done";
        isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        assert isDone : "Task should already be marked as done to mark it as not done";
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task.
     * The icon is "X" if the task is done, otherwise it is a space.
     *
     * @return the status icon of the task
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Retrieves the description of the task.
     *
     * @return the description of the task
     */
    public String getDescription() {
        assert description != null : "Task description must not be null";
        return description;
    }

    /**
     * Returns a string representation of the task.
     * Includes the status icon and description.
     *
     * @return a string representation of the task
     */
    @Override
    public String toString() {
        assert description != null : "Task must have a valid description";
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Returns a string representation of the task in a format suitable for saving.
     * This method must be implemented by subclasses.
     *
     * @return a string representation of the task in save format
     */
    public abstract String toSaveFormat();
}