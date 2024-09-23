package mysutong;

/**
 * Represents a general task in the MySutong application.
 * This class provides the base structure and functionalities for task objects,
 * including support for priorities (1 for high, 2 for medium, and 3 for low).
 */
public class Task {
    protected String description; // Description of the task.
    protected boolean isDone; // Status of the task, whether it is completed or not.
    protected int priority; // Priority of the task (1 = high, 2 = medium, 3 = low).

    /**
     * Constructs a new Task with a specific description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false; // By default, the task is not completed.
        this.priority = 3;   // Default priority is low (3).
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
     * Sets the priority of the task.
     *
     * @param priority The priority level (1 for high, 2 for medium, 3 for low).
     */
    public void setPriority(int priority) {
        if (priority < 1 || priority > 3) {
            throw new IllegalArgumentException("Priority must be 1 (high), 2 (medium), or 3 (low).");
        }
        this.priority = priority;
    }

    /**
     * Gets the priority of the task.
     *
     * @return the priority level of the task (1 for high, 2 for medium, 3 for low).
     */
    public int getPriority() {
        return priority;
    }

    /**
     * Returns a string representation of the priority level.
     *
     * @return a string representing the priority of the task.
     */
    public String getPriorityString() {
        switch (priority) {
        case 1: return "High";
        case 2: return "Medium";
        case 3: return "Low";
        default: return "Unknown";
        }
    }

    /**
     * Returns a string representation suitable for file storage.
     * This method includes the priority of the task in the storage format.
     *
     * @return a formatted string representing the task for file storage.
     */
    public String toFileString() {
        return String.format("%s | %d | %s | %d", this.getClass().getSimpleName().charAt(0), (isDone ? 1 : 0), description, priority);
    }

    /**
     * Returns a string representation of the task, including its status, description, and priority.
     *
     * @return a string representation of the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description + " (Priority: " + getPriorityString() + ")";
    }
}
