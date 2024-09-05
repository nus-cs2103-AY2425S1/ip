package ipman;

/**
 * Represents a task with a description and completion status.
 * Provides methods to mark and unmark the task as done.
 * This class serves as a base class for specific types of tasks.
 *
 * @author miloaisdino
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task with the specified description.
     * The task is initially not done.
     *
     * @param description Name of task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Sets the task to not done
     * @return The new string representation of the task
     */
    public String unmarkStatus() {
        this.isDone = false;
        return this.toString();
    }

    /**
     * Sets the task to done
     * @return The new string representation of the task
     */
    public String markStatus() {
        this.isDone = true;
        return this.toString();
    }

    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
