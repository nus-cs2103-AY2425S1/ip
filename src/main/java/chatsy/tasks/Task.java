package chatsy.tasks;

/**
 * Represents a generic task in the Chatsy application.
 * This class is abstract and serves as the base class for specific task types.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a {@code Task} with the specified description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks this task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks this task as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns the status icon of this task, indicating whether it is done or not.
     *
     * @return The status icon as a string.
     */
    public String getStatusIcon() {
        return (isDone ? "[X] " : "[ ] ");
    }

    /**
     * Returns the description of this task.
     *
     * @return The task description as a string.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the completion status of this task.
     *
     * @return {@code true} if the task is done, {@code false} otherwise.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns the string format of this task for saving to a file.
     *
     * @return The save format of the task as a string.
     */
    public abstract String toSaveFormat();

    /**
     * Returns a string representation of this task.
     *
     * @return The string representation of the task.
     */
    @Override
    public abstract String toString();
}
