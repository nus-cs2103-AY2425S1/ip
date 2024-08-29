package nixy.task;

/**
 * Base class for all tasks, including TodoTask, DeadlineTask, and EventTask.
 * Provides basic functionality for marking tasks as done and getting the task description.
 */
public abstract class Task {
    private String description;
    private boolean isDone;

    /**
     * Creates a task with the specified description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Creates a task with the specified description and done status.
     *
     * @param description The description of the task.
     * @param isDone      The done status of the task.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns the status label of the task. (X if done, empty string otherwise)
     *
     * @return The status label of the task.
     */
    private String getStatusLabel() {
        return (isDone ? "X" : " "); // mark done task with X
    }


    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as undone.
     */
    public void markAsUndone() {
        this.isDone = false;
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
     * Returns the string representation of the task.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusLabel(), this.description);
    }

    /**
     * Returns the string representation of the task in data format (for saving to file)
     *
     * @return The data string.
     */
    public String toDataString() {
        return String.format("%s|%s", this.getStatusLabel(), this.description);
    }
}
