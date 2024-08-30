package myapp.task;

/**
 * The {@code Task} class represents a general task with a description and a completion status.
 * It provides methods to manipulate and retrieve the task's details and status.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new {@code Task} with the specified description.
     * By default, the task is set to not done.
     *
     * @param description A {@code String} describing the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task.
     * "[X]" indicates that the task is done, and "[ ]" indicates that it is not done.
     *
     * @return A {@code String} representing the task's status icon.
     */
    public String getStatusIcon() {
        return (this.isDone ? "[X]" : "[ ]");
    }

    /**
     * Marks the task as done.
     * Sets the {@code isDone} field to {@code true}.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     * Sets the {@code isDone} field to {@code false}.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns the description of the task.
     *
     * @return A {@code String} representing the task's description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Checks if the task is marked as done.
     *
     * @return {@code true} if the task is done; {@code false} otherwise.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Returns a {@code String} representation of the task.
     * The string includes the status icon and the description of the task.
     *
     * @return A {@code String} representation of this task.
     */
    @Override
    public String toString() {
        return getStatusIcon() + " " + this.description;
    }
}
