package broski;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for broski.Task class but should not be used as there are its children classes.
     * @param description the description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns either an X or a whitespace depending on whether task is done or not.
     * @return X or whitespace
     */
    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    /**
     * Marks task as done.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Marks task as undone.
     */
    public void unmark() {
        this.isDone = false;
    }

    protected String getDescription() {
        return this.description;
    }

    public abstract String toStringSave();

    /**
     * Returns a string representation of the task.
     * @return task description with status
     */
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
