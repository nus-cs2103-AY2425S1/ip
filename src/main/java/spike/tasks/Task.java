package spike.tasks;

/**
 * Represents a task in the task list.
 */
public abstract class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructor for a task.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task.
     * X if the task is done, empty string otherwise.
     *
     * @return Status icon of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Returns the description of the task.
     *
     * @return Description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Marks the task as undone.
     */
    public void markAsUndone() {
        isDone = false;
    }

    /**
     * Updates the task based on the update type and updated part.
     *
     * @param updateType The type of update to be made.
     * @param updatedPart The updated part of the task.
     * @return Task with the updated part.
     */
    public abstract Task updateTask(String updateType, String updatedPart);

    /**
     * Returns the string representation of the task to be written to file.
     * Format: | 1 | description
     *
     * @return String representation of the task to be written to file.
     */
    public String toFileString() {
        return " | " + (isDone ? "1" : "0") + " | " + this.description;
    }

    /**
     * Returns the string representation of the task.
     * Format: [status icon] description
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Returns the type of the task in String format.
     *
     * @return Type of the task in String format.
     */
    public String getTaskType() {
        return this.getClass().getSimpleName();
    }
}
