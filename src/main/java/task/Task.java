package task;

/**
 * Represents a task that can be tracked by Handsome.
 */
public class Task {
    public String description;
    public boolean isDone;

    /**
     * Constructs a new task with the specified description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task with "X" as done.
     *
     * @return A String representing the task's completion status.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : "  ");
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

    @Override
    public String toString() {
        return ("[" + this.getStatusIcon() + "] " + this.description);
    }
}