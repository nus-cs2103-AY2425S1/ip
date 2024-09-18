package henry.task;

/**
 * Represents a task to be recorded. A <code>Task</code> object
 * is represented by one String.
 * e.g., <code>return book</code>
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Sets up the instance.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Sets up the instance.
     *
     * @param description Description of the task.
     * @param isDone Whether the task is completed.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns whether the task is done or not.
     *
     * @return Status of task using symbol.
     */
    public String getStatusIcon() {
        return (this.isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns description of task.
     *
     * @return Description of task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Indicates that the task is done.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Indicates that the task is not done.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Returns whether the task is done or not.
     *
     * @return The status of the task.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Returns summary of the task.
     *
     * @return Task summary.
     */
    public String summary() {
        return "| " + (this.isDone() ? "1" : "0") + " | "
                + this.getDescription();
    }

    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
