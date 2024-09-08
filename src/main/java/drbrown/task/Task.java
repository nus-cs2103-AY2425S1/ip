package drbrown.task;

/**
 * Represents an abstract task in the DrBrown application.
 * A task has a description and a status indicating whether it is completed.
 * Specific types of tasks should extend this class and implement its abstract methods.
 */
public abstract class Task {

    private boolean isCompleted;
    private final String description;

    /**
     * Constructs a Task with the specified status and description.
     *
     * @param isCompleted  The completion status of the task (true if completed, false otherwise).
     * @param description  The description of the task.
     */
    public Task(Boolean isCompleted, String description) {
        assert isCompleted != null : "Task status should not be null";
        assert description != null : "Task description should be null";
        this.isCompleted = isCompleted;
        this.description = description;
    }

    /**
     * Constructs a Task with a default description.
     * The default description is an empty string, and the task status is not set.
     */
    public Task() {
        this.isCompleted = false;
        this.description = "";
    }

    /**
     * Returns the completion status of the task.
     *
     * @return true if the task is completed, false otherwise.
     */
    public boolean getStatus() {
        return isCompleted;
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
     * Sets the completion status of the task.
     *
     * @param status The new completion status of the task (true if completed, false otherwise).
     */
    public void setStatus(Boolean status) {
        assert status != null : "Task status should not be null";
        this.isCompleted = status;
    }

    /**
     * Returns the string representation of the task in the format suitable for file storage.
     *
     * @return A string formatted for file storage representing the task.
     */
    public abstract String toFileString();

    /**
     * Returns the string representation of the task in the format suitable for UI display.
     *
     * @return A user-friendly string that describes the task.
     */
    public abstract String toUiString();
}
