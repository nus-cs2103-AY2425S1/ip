package tasks;

/**
 * Represents a task with a description and a completion status.
 * This is an abstract class that other specific task types will extend.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task with the specified description.
     *
     * @param description the description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task.
     * "1" indicates the task is done, "0" indicates it is not done.
     *
     * @return the status icon as a String
     */
    public String getStatusIcon() {
        return (isDone ? "1" : "0");
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
     * Returns the string representation of the task for display purposes.
     * This method must be implemented by subclasses to provide specific details.
     *
     * @return the string representation of the task
     */
    @Override
    public abstract String toString();

    /**
     * Returns the string representation of the task suitable for saving to a file.
     * This method must be implemented by subclasses to ensure proper formatting.
     *
     * @return the string representation of the task for file storage
     */
    public abstract String toFileString();

}

