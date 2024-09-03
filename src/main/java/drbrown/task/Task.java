package drbrown.task;

/**
 * Represents an abstract task in the DrBrown application.
 * A task has a description and a status indicating whether it is completed.
 * Specific types of tasks should extend this class and implement its abstract methods.
 */
public abstract class Task {

    private boolean status;
    private String description;

    /**
     * Constructs a Task with the specified status and description.
     *
     * @param status      The completion status of the task (true if completed, false otherwise).
     * @param description The description of the task.
     */
    public Task(boolean status, String description) {
        this.description = description;
        this.status = status;
    }

    /**
     * Default constructor for Task.
     */
    public Task() {}

    /**
     * Returns the completion status of the task.
     *
     * @return true if the task is completed, false otherwise.
     */
    public boolean getStatus() {
        return this.status;
    }

    /**
     * Returns the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Sets the completion status of the task.
     *
     * @param status The new completion status of the task (true if completed, false otherwise).
     */
    public void setStatus(boolean status) {
        this.status = status;
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
    public abstract String toUIString();
}
