package eevee.task;

/**
 * Represents a Task object.
 */
public class Task {
    public enum Priority {
        LOW, MEDIUM, HIGH
    }
    protected String description;
    protected boolean isDone;
    protected Priority priority;

    /**
     * Constructs a new Task with the specified description string.
     * Sets Task as not done initially.
     *
     * @param description The string describing the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.priority = Priority.LOW;
    }

    /**
     * Returns current priority of task. Default priority is LOW.
     *
     * @return The level of priority of the task.
     */
    public Priority getPriority() {
        return this.priority;
    }

    /**
     * Sets the priority level of the task.
     *
     * @param priority The priority level to be set as.
     */
    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    /**
     * Returns Task description.
     *
     * @return Task String description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns whether Task is done or not.
     *
     * @return Task boolean isDone.
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Returns the status icon of the task.
     *
     * @return "[X]" if the task is done, "[ ]" if it is not done.
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Unmarks the task as done.
     */
    public void unmarkAsDone() {
        this.isDone = false;
    }

    /**
     * Returns the status of the task.
     *
     * @return 1 if the task is done, 0 if it is not done.
     */
    public int getStatus() {
        return (isDone ? 1 : 0);
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }

    /**
     * Returns string to be stored in the storage file.
     *
     * @return "|(status)|(description)" whereby (status) and (description) are the task's status and description.
     */
    public String toFileString() {
        return "|" + getStatus() + "|" + description + "|" + priority;
    }
}
