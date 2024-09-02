package eevee;

/**
 * Represents a Task object
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new Task with the specified description string.
     * Sets Task as not done initially.
     *
     * @param description The string describing the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
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
        return "|" + getStatus() + "|" + description;
    }
}
