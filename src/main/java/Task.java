/**
 * The Task class keeps track of whether a specific action has been completed or not.
 */
public class Task {

    protected String description;
    protected boolean isDone;

    /**
     * Creates a new Task Instance.
     *
     * @param description the description of the task created.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the String representation of the task's completion status.
     *
     * @return the icon of the task's completion status
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the String representation of how the task would appear when displayed in a list.
     *
     * @return the String representation of the entire task.
     */
    public String getTaskString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}