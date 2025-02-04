package dgpt.task;

/**
 * Represents a generic task with a description and a completion status.
 * <p>
 * This class is an abstract base class for different types of tasks in the Dgpt application.
 * It provides methods for managing and representing the task's description and completion status.
 * </p>
 */
public abstract class Task {

    protected String description;
    protected boolean isDone;

    /**
     * Creates a new {@code Task} instance with the specified description.
     *
     * @param description The description of the task being created.
     */
    public Task(String description) {
        assert description != null && !description.isBlank() : "description cannot be null or empty";
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the string representation of the task's completion status.
     * <p>
     * The status icon is represented as "X" if the task is completed, or " " (a space) if the task is not completed.
     * </p>
     *
     * @return The icon representing the task's completion status.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as completed.
     * <p>
     * This method sets the {@code isDone} status of the task to {@code true}.
     * </p>
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Marks the task as not completed.
     * <p>
     * This method sets the {@code isDone} status of the task to {@code false}.
     * </p>
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Returns the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns whether the task is completed.
     *
     * @return {@code true} if the task is completed, {@code false} otherwise.
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

}
