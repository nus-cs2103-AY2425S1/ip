package duck.task;

/**
 * Represents a task with a description and completion status.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a duke.task.Task with description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks this task as done.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Marks this task as not done.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Returns the description of this task.
     *
     * @return The description of this task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the status icon of this task. An X means that the task is done.
     *
     * @return The status icon of this task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns a string representation of this task, including its completion status.
     *
     * @return A string representation of this task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    public abstract String toFileString();
}
