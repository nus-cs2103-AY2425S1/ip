package moody.tasks;

/**
 * Represents a task with a description and completion status.
 * The Task class provides methods to manage and display the task's status.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a Task with the specified description.
     * The task is initially marked as not done.
     *
     * @param description The description of the Task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the Task.
     * The icon is "X" if the task is done, otherwise a blank space.
     *
     * @return The status icon of the Task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return description;
    }

    /**
     * Marks the Task as done.
     * Updates the task status to indicate completion.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the Task as not done.
     * Updates the task status to indicate that it is incomplete.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Converts the Task to a format suitable for saving to a file.
     * The format includes the completion status and the description.
     *
     * @return A string representation of the Task in file format.
     */
    public String toFileFormat() {
        return (isDone ? "1" : "0") + " | " + this.description;
    }

    /**
     * Returns a string representation of the Task for display purposes.
     * The format includes the status icon and the description.
     *
     * @return A string representation of the Task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), this.description);
    }
}
