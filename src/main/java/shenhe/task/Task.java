package shenhe.task;

/**
 * Abstract class representing a task.
 * This class provides basic functionality for managing the status and description of a task.
 */
public abstract class Task {
    /** The description of the task. */
    protected String description;

    /** Indicates whether the task is done. */
    protected boolean isDone;

    /**
     * Constructs a Task with the specified description and status.
     *
     * @param description the description of the task
     * @param isDone      the status of the task (true if done, false otherwise)
     */
    public Task(String description, boolean isDone) {
        // Assert that the description is not null and not empty
        assert description != null : "Task description should not be null";
        assert !description.trim().isEmpty() : "Task description should not be empty";

        this.description = description;
        this.isDone = isDone;
    }


    /**
     * Returns the status icon of the task.
     * The icon is "1" if the task is done, and "0" otherwise.
     *
     * @return the status icon of the task
     */
    public String getStatusIcon() {
        return (isDone ? "1" : "0");
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks the task as undone.
     */
    public void markAsUndone() {
        isDone = false;
    }

    /**
     * Checks if the task is done.
     *
     * @return true if the task is done, false otherwise
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Returns a string representation of the task in a format suitable for saving to a file.
     * This method must be implemented by subclasses.
     *
     * @return a string representation of the task in file format
     */
    public abstract String toFileFormat();

    /**
     * Returns a string representation of the task.
     * The string includes the status icon and the task description.
     *
     * @return a string representation of the task
     */
    @Override
    public String toString() {
        String statusIcon = getStatusIcon();
        // Assert that status icon is either "1" or "0"
        assert statusIcon.equals("1") || statusIcon.equals("0") : "Status icon should be '1' or '0'";

        // Assert that description is not null
        assert description != null : "Task description should not be null";

        return "[" + statusIcon + "] " + description;
    }

}
