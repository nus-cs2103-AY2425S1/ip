package pikappi.task;

/**
 * Represents a task in Pikappi.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected String taskType;

    /**
     * Creates a new Task object.
     *
     * @param description The description of the task.
     */
    public Task(String description, String taskType) {
        this.description = description;
        this.isDone = false;
        this.taskType = taskType;
    }

    /**
     * Creates a new Task object.
     *
     * @param description The description of the task.
     * @param isDone The status of the task.
     */
    public Task(String description, String taskType, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
        this.taskType = taskType;
    }

    /**
     * Returns the status icon of the task.
     *
     * @return The status icon of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Unmarks the task as not done.
     */
    public void unmarkAsDone() {
        this.isDone = false;
    }

    /**
     * Returns the status of the task.
     *
     * @return The status of the task.
     */
    public boolean isDone() {
        return isDone;
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
     * Returns the type of the task.
     *
     * @return The type of the task.
     */
    public String getTaskType() {
        return this.taskType;
    }

    /**
     * Returns the string representation of the task.
     *
     * @return The string representation of the task.
     */
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
