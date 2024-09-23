package mysutong;

/**
 * Represents a general task in the MySutong application.
 * This class provides the base structure and functionalities for task objects.
 */
public class Task {
    protected String description; // Description of the task.
    protected boolean isDone; // Status of the task, whether it is completed or not.

    /**
     * Constructs a new Task with a specific description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false; // By default, the task is not completed.
    }

    /**
     * Returns the completion status of the task.
     *
     * @return true if the task is completed, otherwise false.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns a status icon indicating whether the task is completed.
     * 'X' represents completed, and a space represents incomplete.
     *
     * @return a string representing the completion status icon.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // 'X' for done, ' ' for not done.
    }

    /**
     * Marks the task as completed.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks the task as not completed.
     */
    public void unmark() {
        isDone = false;
    }

    /**
     * Returns a string representation suitable for file storage.
     * This method should be overridden by subclasses to match their specific storage format.
     *
     * @return a formatted string representing the task for file storage.
     */
    public String toFileString() {
        return String.format("%s | %d | %s", this.getClass().getSimpleName().charAt(0), (isDone ? 1 : 0), description);
    }

    /**
     * Returns a string representation of the task, including its status and description.
     *
     * @return a string representation of the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
