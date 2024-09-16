package nixy.task;

/**
 * Base class for all tasks, including TodoTask, DeadlineTask, and EventTask.
 * Provides basic functionality for marking tasks as done and getting the task description.
 */
public abstract class Task implements ReadableTask {
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
     *
     * @return true if the task was previously not done, false otherwise.
     */
    public boolean markAsDone() {
        boolean prevIsDone = this.isDone;
        this.isDone = true;
        return prevIsDone != this.isDone;
    }

    /**
     * Marks the task as undone.
     *
     * @return true if the task was previously done, false otherwise.
     */
    public boolean markAsUndone() {
        boolean prevIsDone = this.isDone;
        this.isDone = false;
        return prevIsDone != this.isDone;
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
     * Returns the done status of the task.
     * @return The done status of the task.
     */
    public boolean isDone() {
        return this.isDone;
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
     * Returns the string representation of the task in data format (for saving to file).
     *
     * @return The data string.
     */
    @Override
    public String toDataString() {
        return String.format("%s|%s", this.getStatusLabel(), this.description);
    }
}
