package sage.task;

/**
 * Represents an abstract task in the task management application
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a task with the specified description
     * The task is initially marked as not done
     *
     * @param description The description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon representing whether the task is done
     *
     * @return A string "[X]" if the task is done or "[ ]" if it is not
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); //Mark done task with X
    }

    /**
     * Marks the task as done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }
    public abstract String toFileFormat();

    public boolean containsKeyword(String keyword) {
        return this.description.toLowerCase().contains(keyword.toLowerCase());
    }
}
