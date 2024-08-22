/**
 * Represents a task that can be marked done.
 */
public abstract class Task {

    /**
     * The description of the task.
     */
    protected String description;

    /**
     * The status of the task, either done or not.
     */
    protected boolean isDone;

    /**
     * Creates a task with the given description and by default, the task is not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
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
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Returns the string representation of the task.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString () {
        return (isDone ? "[X]" : "[ ]") + " " + description;
    }
}
