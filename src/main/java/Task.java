public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task class but should not be used as there are its children classes.
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

    /**
     * Returns a string representation of the task.
     * @return task description with status
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
