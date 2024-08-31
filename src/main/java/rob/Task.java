package rob;

/**
 * Represents the tasks to be added by user.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a task that user can add to list
     *
     * @param description Name of the class to be added.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    public void markAsDone() {
        isDone = true;
    }

    public void unmark() {
        isDone = false;
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }

    public String toSaveString() {
        return getStatusIcon() + " | " + description;
    }

}

