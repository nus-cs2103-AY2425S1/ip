package tasks;

/**
 * Represents an abstract task with a description and completion status.
 * This class is meant to be extended by specific types of tasks.
 */
public abstract class Task {

    /** The description of the task. */
    protected String description;

    /** The completion status of the task. */
    protected boolean isDone;

    /**
     * Constructs a Task with the specified description.
     * The task is initially not marked as done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon for the task.
     *
     * @return "X" if the task is marked as done, otherwise a blank space.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as done.
     */
    public void mark() {
        isDone = true; // mark task done
    }

    /**
     * Unmarks the task, setting it as not done.
     */
    public void unmark() {
        isDone = false; // unmark task done
    }

    /**
     * Returns a formatted string representation of the task for storage.
     *
     * @return A string representing the task in a format suitable for storage.
     */
    public String toDataFormat() {
        return (isDone ? "1" : "0") + " | " + description;
    }

    /**
     * Returns a formatted string representation of the task for display,
     * including its status icon and description.
     *
     * @return A string representing the task with its status and description.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
