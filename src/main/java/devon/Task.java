package devon;

/**
 * Represents an abstract task in the Devon application.
 * A task has a description and a status indicating whether it is done or not.
 * Subclasses must provide their specific format for database storage.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new Task with the specified description.
     * The task is initially marked as not done.
     *
     * @param description A brief description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task.
     *
     * @return A string representing the task status; "X" if the task is done, otherwise a space.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
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
     * Marks the task as done and returns a confirmation message.
     *
     * @return A string confirming that the task has been marked as done.
     */
    public String markAsDone() {
        isDone = true;
        return "\tNice! I've marked this task as done:\n\t\t" + this;
    }

    /**
     * Marks the task as done without returning a message.
     */
    public void markAsDoneSilently() {
        isDone = true;
    }

    /**
     * Marks the task as not done and returns a confirmation message.
     *
     * @return A string confirming that the task has been marked as not done.
     */
    public String markAsUndone() {
        isDone = false;
        return "\tOK, I've marked this task as not done yet:\n\t\t" + this;
    }

    /**
     * Returns a message announcing the deletion of the task, but does not actually delete the task from the TaskList.
     *
     * @return A string confirming that the task has been removed.
     */
    public String announceDeletion() {
        return "\tNoted. I've removed this task:\n\t\t" + this;
    }

    /**
     * Provides a string representation of the task in a format suitable for database storage.
     * Subclasses must implement this method to return their specific format.
     *
     * @return A string representing the task in a format suitable for database storage.
     */
    public abstract String dbReadableFormat();

    /**
     * Returns a string representation of the task for display purposes.
     *
     * @return A string representing the task, including its status icon and description.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), getDescription());
    }
}
