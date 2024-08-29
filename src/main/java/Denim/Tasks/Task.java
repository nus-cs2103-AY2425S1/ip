package denim.tasks;

/**
 * Represents a task with a description and a completion status.
 * This class provides methods to manage and retrieve the task's details.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new Task with the specified description.
     * The task is initially marked as not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    /**
     * Constructs a new Task with the specified description and completion status.
     *
     * @param description The description of the task.
     * @param isDone      The initial completion status of the task.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns a symbolic representation of the task's completion status.
     *
     * @return "X" if the task is done, otherwise a space " ".
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns the completion status of the task.
     *
     * @return True if the task is done, false otherwise.
     */
    public boolean getIsDone() {
        boolean result = isDone;
        return result;
    }

    /**
     * Returns the description of the task.
     *
     * @return The task's description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the completion status of the task.
     *
     * @param isDone The new completion status of the task.
     */
    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Returns a string representation of the task.
     * The string includes the task's completion status and description.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        String taskMessage = String.format("[%s] %s", getStatusIcon(), getDescription());
        return taskMessage;
    }

    /**
     * Returns a simplified string representation of the task.
     * This method is intended to be overridden by subclasses to provide a specific format.
     *
     * @return A simplified string representation of the task, or null if not overridden.
     */
    public String toSimplifiedString() {
        return null;
    }
}
