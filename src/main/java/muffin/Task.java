package muffin;

/**
 * Represents a general task with a description and a completion status.
 * It serves as a base class for more specific types of tasks such as Todos, Deadlines, and Events.
 */
public abstract class Task {

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
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Checks the status of a task and returns "1" if the task is done, or "0" if it is not.
     *
     * @param task The task whose status is being checked.
     * @return A string "1" if the task is done, or "0" if it is not.
     */
    public String checkStatus(Task task) {
        if (task.isDone) {
            return "1";
        } else {
            return "0";
        }
    }

    public abstract String toFormattedString();

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}
