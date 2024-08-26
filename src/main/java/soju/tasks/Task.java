package soju.tasks;

/**
 * The {@code Task} class represents a task with a description and a status indicating whether it is done.
 * It is an abstract class that provides common functionality for different types of tasks.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new {@code Task} with the specified description.
     * The task is initially marked as not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    /**
     * Returns a status icon representing whether the task is done or not.
     * The icon is "X" if the task is done, and " " (a space) if the task is not done.
     *
     * @return The status icon as a string.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }
    /**
     * Returns the description of the task.
     *
     * @return The description of the task as a string.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Unmarks the task (marks it as not done).
     */
    public void unmark() {
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

    /**
     * Returns a string representation of the task formatted for saving to a file.
     *
     * @return A string representation of the task formatted for file storage.
     */
    public abstract String toFileString();
}
