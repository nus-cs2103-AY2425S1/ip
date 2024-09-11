package muffin;

/**
 * Represents a general task with a description and a completion status.
 * It serves as a base class for more specific types of tasks such as Todos, Deadlines, and Events.
 */
public class Task {

    /**
     * A brief description of the task.
     */
    protected String description;

    /**
     * A boolean indicating whether the task is done.
     */
    protected boolean isDone;


    /**
     * Constructs a new Task with the specified description.
     * By default, the task is marked as not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        assert description != null;

        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}
