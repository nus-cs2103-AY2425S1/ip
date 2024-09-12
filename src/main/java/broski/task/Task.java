package broski.task;

/**
 * Abstract class that acts as a parent class for 3 subcategories of tasks.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new general task.
     * Not in use.
     * @param description the description of the task
     */
    protected Task(String description) {
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

    public String getDescription() {
        return this.description;
    }

    /**
     * Returns a string representation needed for the save file.
     */
    public abstract String toStringSave();

    /**
     * Returns a string representation of the task.
     * @return Task description with status.
     */
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
