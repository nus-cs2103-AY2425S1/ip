package weeny;

/**
 * Represents a task with a description and completion status.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected TaskType type;

    /**
     * Creates a new task with the specified description and type.
     *
     * @param description The description of the task.
     * @param type The type of the task.
     */
    public Task(String description, TaskType type) {
        this.description = description;
        this.isDone = false;
        this.type = type;
    }

    /**
     * Returns the status icon for the task.
     *
     * @return "X" if the task is done, otherwise a space.
     */
    public String getStatusIcon() {
        return (this.isDone ? "X" : " ");
    }

    /**
     * Returns the description of the task.
     *
     * @return The description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task for output.
     *
     * @return A string with task details.
     */
    public String toOutput() {
        return " ";
    }

    @Override
    /**
     * Returns a string representation of the task.
     *
     * @return A string in the format "[statusIcon] description".
     */
    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }
}
