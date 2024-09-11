package yoda.tasks;

/**
 * Represents an abstract task with a description and completion status.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a new Task with the specified description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Retrieves the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Retrieves the status icon of the task, indicating whether it is done.
     *
     * @return "X" if the task is done, otherwise a space " ".
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markNotDone() {
        this.isDone = false;
    }

    /**
     * Returns a formatted string representing the task, to be saved to a file.
     *
     * @return A string representing the task data for storage.
     */
    public abstract String getData();

    /**
     * Returns a string representation of the task, including its status and description.
     *
     * @return A string in the format "[status] description".
     */
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), description);
    }
}
