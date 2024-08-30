package bill;

/**
 * The Task class allows for initialising of general tasks
 * It is used predominantly as a parent class to be inherited from.
 */
public class Task {
    private String description;
    private boolean isDone;

    /**
     * Initializes Task.
     *
     * @param description Description of task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return description;
    }

    public void mark() {
        isDone = true;
    }

    public void unmark() {
        isDone = false;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }
}
