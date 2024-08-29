package Task;

/**
 * Represents a task to be completed. A task can be of 3 different types and can be marked as done or not done.
 *
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status of the task. If task is done, return an "X", else, return a " ".
     *
     * @return Status icon of task as a String.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns the type of the task.
     *
     * @return Type of task.
     */
    public String getType() {
        return "Task";
    }

    /**
     * Returns the description of the task.
     *
     * @return Task description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns whether the task is done.
     *
     * @return Status of task.
     */
    public boolean isDone() {
        return this.isDone;
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
     * Returns string representation of the task with the status icon followed by the description.
     *
     * @return String representation of task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
