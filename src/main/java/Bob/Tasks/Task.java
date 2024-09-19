package bob.tasks;

/**
 * Represents a task in Bob.
 * A task has a description and a status indicating whether it is done.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task with the specified description.
     * The task is initially not marked as done.
     *
     * @param description the description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task.
     * An "X" indicates the task is done, and a space indicates it is not done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String getDescription() {
        return this.description;
    }


    public void mark() {
        isDone = true;
    }

    public void unmark() {
        isDone = false;
    }

    /**
     * Returns the task in a file format string representation.
     * This format is used for saving the task to a file.
     *
     * @return the file format string representation of the task.
     */
    public String fileFormat() {
        return (isDone ? "1" : "0") + " | " + description;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}
