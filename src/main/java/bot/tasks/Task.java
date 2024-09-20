package bot.tasks;

/**
 * Represents a generic completable task.
 *
 * @author mongj
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a new <code>Task</code> object.
     *
     * @param description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Creates a new <code>Task</code> object.
     *
     * @param description of the task.
     * @param isDone indicating if the task is completed.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public boolean isDone() {
        return isDone;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsIncomplete() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Generates a string representation of the data
     * for storing in the data file.
     *
     * @return String representation of the data.
     */
    public String toData() {
        return description + " | " + isDone;
    }
}
