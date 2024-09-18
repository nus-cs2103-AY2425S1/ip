package Naega.Task;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a new Task with the specified description.
     * Initializes the task as not done.
     *
     * @param description the description of the task
     */
    public Task(String description) {
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
        isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
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