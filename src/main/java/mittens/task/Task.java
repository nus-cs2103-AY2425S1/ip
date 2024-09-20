package mittens.task;

/**
 * Represents a task in the task list.
 */
public abstract class Task {
    
    protected String description;
    protected boolean isDone;

    /**
     * Creates a task with the given description.
     * 
     * @param description The description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns a text representation of whether the task is done.
     * 
     * @return "X" if the task is done, " " otherwise
     */
    public String getStatusIcon() {
        return (this.isDone ? "X" : " ");
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
