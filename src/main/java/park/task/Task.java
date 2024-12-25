package park.task;

/**
 * Represents a Task that can be marked and unmarked as done.
 */
public abstract class Task {

    protected String description;
    protected boolean isDone;

    /**
     * Constructs a task that is not done by default.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Encodes the task by splitting its attributes by "/".
     *
     * @return String to be written to a saved file.
     */
    public abstract String encode();

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    /**
     * Marks the task as done.
     */
    public void mark() {
        isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void unmark() {
        isDone = false;
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + " " + description;
    }
}
