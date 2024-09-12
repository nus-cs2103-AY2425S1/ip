package tira.task;

/**
 * Task class serves as parent class for the sub-classes.
 */
public class Task { // used Task class from the Week 2 IP Page
    protected String description;
    protected boolean isDone;

    /**
     * Represents a task with a description and a status indicating whether it is done.
     * A task can be marked as done or not done, and its description can be retrieved.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status of the task, indicating whether it is done.
     *
     * @return {@code true} if the task is done, {@code false} otherwise.
     */
    public boolean getIsDone() {
        return isDone;
    }

    /**
     * Returns the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the status icon of the task, which is "X" if the task is done
     * or a space " " if it is not done.
     *
     * @return The status icon of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as done.
     */
    public void markStatus() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void unmarkStatus() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task, which includes its
     * status icon and its description.
     *
     * @return A string representing the task.
     */
    public String toString() {
        return "["
                + this.getStatusIcon()
                + "] "
                + description.trim();
    }
}
