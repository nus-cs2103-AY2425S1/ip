package asura.data.tasks;

/**
 * Represents a Task.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a Task with the specified description.
     * @param description The description of the Task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    public boolean getIsDone() {
        return isDone;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks the task as undone.
     */
    public void markAsNotDone() {
        isDone = false;
    }

    public String toString() {
        return this.getStatusIcon() + " " + this.description;
    }
}
