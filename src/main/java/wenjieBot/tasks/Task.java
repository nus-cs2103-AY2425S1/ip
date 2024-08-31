package wenjieBot.tasks;

/**
 * The Task class represents a general task in the wenjieBot application.
 * It includes information about the task's description and completion status.
 */
public class Task {

    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task with the specified description.
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
     * The icon is "X" if the task is done, and a blank space if not done.
     *
     * @return A string representing the status icon of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Sets the completion status of the task.
     *
     * @param newStatus The new completion status of the task.
     */
    public void setStatusIcon(boolean newStatus) {
        this.isDone = newStatus;
    }

    /**
     * Returns whether the task is done.
     *
     * @return true if the task is done, false otherwise.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns a string representation of the Task in a format suitable for storage.
     * The format includes the completion status and description.
     *
     * @return A string representation of the Task for storage.
     */
    public String toPrettierString() {
        if (isDone) {
            return " | 1 | " + this.description;
        } else {
            return " | 0 | " + this.description;
        }
    }

    /**
     * Returns the description of the task.
     * This method provides access to the textual description that defines the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns a string representation of the Task for display purposes.
     * The format includes a status indicator and the description.
     *
     * @return A string representation of the Task for display.
     */

    @Override
    public String toString() {
        if (isDone) {
            return "[X] " + this.description;
        } else {
            return "[ ] " + this.description;
        }
    }
}
