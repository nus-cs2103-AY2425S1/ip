package pebble;

/**
 * Class to abstract and encapsulate subclasses of tasks.
 */
public abstract class Task {
    /** Description of task */
    protected String description;
    /** Marker to track completion of task */
    protected boolean isDone;

    /**
     * Constructor class
     * @param description Description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void unmarkAsNotDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
