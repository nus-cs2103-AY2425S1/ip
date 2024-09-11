package nugget;

/**
 * The {@code Task} class represents a task with a description and a completion status.
 * It provides methods to mark a task as done or undone, and to get a formatted string
 * representation of the task.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a {@code Task} with the specified description.
     * The task is initially marked as not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task.
     * The icon is "X" if the task is done, and " " (a space) if the task is not done.
     *
     * @return The status icon of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as done.
     */
    public void markTask() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void unmarkTask() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task.
     * The string is formatted as "[statusIcon] description", where statusIcon is "X" or " "
     * depending on whether the task is done, and description is the task's description.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }

    /**
     * Checks if the task is completed.
     *
     * @return {@code true} if the task is done, {@code false} otherwise.
     */
    public boolean isCompleted() {
        return this.isDone;
    }
}
