package lama.task;

/**
 * Represent a task with a string description and a boolean status indicating whether the task is done.
 */
public abstract class Task {
    /** Description of the task */
    protected String description;

    /** Whether the task is done */
    protected boolean isDone;

    /**
     * Construct a Task with specified description.
     *
     * @param description Description of a task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task.
     * The status icon is "X" if the task is done, otherwise it is a space string.
     *
     * @return Status icon as a string.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Mark the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Mark the task as undone.
     */
    public void markAsUnDone() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task.
     * The format of the string is "[status] description".
     *
     * @return String representation of a task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Abstract method to convert the task to a string format that is suitable to save in file.
     * Implemented by subclass.
     *
     * @return String representation of the task suitable to save in the file.
     */
    public abstract String toFileFormat();
}
