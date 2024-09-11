package nether.task;

/**
 * Represents a task object model with a description and status.
 * The {@code Task} class serves as an abstract base class for different types of tasks, providing methods to manage the
 * task's status and description.
 */

public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs (by default) an incomplete {@code Task} object with the specified description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task.
     * The status icon is represented as "X" if the task is marked as done, or a space if not.
     *
     * @return A string representing the task's status icon.
     */
    public String getStatusIcon() {
        return (this.isDone ? "X" : " ");
    }

    /**
     * Returns the description of the task.
     *
     * @return A string representing the task's description.
     */
    public String getDescription() {
        return this.description;
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
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task in a format suitable for saving.
     * This method must be implemented by subclasses to provide a format specific to the type of task.
     *
     * @return A string in the format suitable for saving the task.
     */
    public abstract String toSaveFormat();

    /**
     * Returns whether the task is marked as done.
     *
     * @return {@code true} if the task is done; {@code false} otherwise.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Returns a string representation of the task.
     * The format is: {@code [statusIcon] description}, where {@code statusIcon} is "X" if the task is done.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.getDescription());
    }

}
