package kafka;

/**
 * Represents a task with a description and a completion status.
 */
public class Task {

    /** The task description. */
    public final String description;

    /** The completion status of the task. */
    public boolean isDone;

    /**
     * Constructs a Task with a description and its status.
     *
     * @param description The description of the task.
     * @param isDone The completion status of the task.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns the status icon representing whether the task is done.
     *
     * @return "X" if done, otherwise a space.
     */
    public String getStatusIcon() {
        return (this.isDone ? "X" : " ");
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        if (this.isDone) {
            return;
        }
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        if (!this.isDone) {
            return;
        }
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return A string showing the status icon and description of the task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
