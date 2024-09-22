package drbrown.task;

/**
 * Represents an abstract task in the DrBrown application.
 * A task has a description, a completion status, and a priority level.
 * Specific types of tasks should extend this class and implement its abstract methods.
 */
public abstract class Task {

    /**
     * Enum representing the priority levels of a task.
     */
    public static enum Priority {
        LOW,
        MEDIUM,
        HIGH
    }

    private boolean isCompleted;
    private String description;
    private Priority priority;

    /**
     * Constructs a Task with the specified status, description, and priority.
     *
     * @param isCompleted  The completion status of the task (true if completed, false otherwise).
     * @param description  The description of the task.
     * @param priority     The priority level of the task.
     * @throws AssertionError if isCompleted or description is null.
     */
    public Task(Boolean isCompleted, String description, Priority priority) {
        assert isCompleted != null : "Task status should not be null";
        assert description != null : "Task description should not be null";
        this.isCompleted = isCompleted;
        this.description = description;
        this.priority = priority;
    }

    /**
     * Constructs a Task with a default description and priority.
     * The default description is an empty string, and the task is not completed with LOW priority.
     */
    public Task() {
        this.isCompleted = false;
        this.description = "";
        this.priority = Priority.LOW;
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
     * Returns the priority level of the task.
     *
     * @return The priority level of the task.
     */
    public Priority getPriority() {
        return this.priority;
    }

    /**
     * Sets the priority level of the task.
     *
     * @param priority The new priority level for the task.
     */
    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    /**
     * Sets the completion status of the task.
     *
     * @param status The new completion status of the task (true if completed, false otherwise).
     * @throws AssertionError if status is null.
     */
    public void setStatus(Boolean status) {
        assert status != null : "Task status should not be null";
        this.isCompleted = status;
    }

    /**
     * Returns the string representation of the task in the format suitable for file storage.
     * The format is: " | isCompleted | description | priority ".
     *
     * @return A string formatted for file storage representing the task.
     */
    public String toFileString() {
        return " | " + this.getStatus() + " | " + this.getDescription() + " | " + this.priority;
    }

    /**
     * Returns the string representation of the task in the format suitable for UI display.
     * This method must be implemented by subclasses to provide specific UI formatting.
     *
     * @return A user-friendly string that describes the task.
     */
    public abstract String toUiString();

    /**
     * Returns a string representation of the task for display purposes.
     * The format is: "[X] description | priority" where "X" indicates completion.
     *
     * @return A string representing the task in a human-readable format.
     */
    @Override
    public String toString() {
        return "[" + (this.getStatus() ? "X" : " ") + "] " + this.getDescription() + " | " + this.priority;
    }
}
