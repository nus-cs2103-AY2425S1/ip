/**
 * Represents a task with a description and completion status.
 */
public class Task {
    private final String description;  // The task's description
    private boolean isDone;            // Indicates if the task is completed

    /**
     * Constructs a new Task with the specified description.
     * The task is initially marked as not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Sets the completion status of the task.
     *
     * @param status True to mark the task as done, false to mark it as not done.
     */
    public void setStatus(boolean status) {
        this.isDone = status;
    }

    /**
     * Returns the status icon representing whether the task is done.
     * An "X" indicates the task is done; a space indicates it is not done.
     *
     * @return A string containing "X" if the task is done, otherwise a space.
     */
    private String getStatusIcon() {
        return (this.isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns a string representation of the task, including its status icon.
     *
     * @return The task's status icon followed by its description.
     */
    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.description;
    }
}
