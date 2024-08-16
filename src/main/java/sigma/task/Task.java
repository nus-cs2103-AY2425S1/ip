package sigma.task;

/**
 * Represents a task with a description and a status.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for a Task.
     *
     * @param description the description of the task
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
     * Returns the status icon of this task.
     *
     * @return "X" if the task is done, otherwise " "
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Checks if the task's description contains the specified keyword.
     * The comparison is case-insensitive.
     *
     * @param keyword The keyword to search for in the task's description.
     * @return {@code true} if the description contains the keyword, {@code false} otherwise.
     */
    public boolean containsKeyword(String keyword) {
        return this.description.toLowerCase().contains(keyword.toLowerCase());
    }

    /**
     * Converts this task to a string representation.
     *
     * @return the string representation of this task
     */
    public abstract String stringify();

    /**
     * Returns the string representation of this task.
     *
     * @return the string representation of this task in the format "[status] description"
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}
