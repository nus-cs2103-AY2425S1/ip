public abstract class Task {
    /**
     * The task description.
     */
    protected String description;

    /**
     * Whether this task is done.
     */
    protected boolean isDone;

    /**
     * The constructor.
     *
     * @param description Task description.
     */
    public Task(String description) throws BocchiException {
        if (description == null) {
            throw new BocchiException("So..sorry, but the task description can't be empty.");
        }
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks this task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks this task as not done.
     */
    public void markAsUnDone() {
        isDone = false;
    }

    /**
     * Gets whether this task is done.
     *
     * @return Whether this task is done.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Gets the task description.
     *
     * @return The task description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns a string representation of this task.
     *
     * @return A string representation of this task.
     */
    @Override
    public String toString() {
        return (isDone ? "[X]" : "[ ]") + " " + description;
    }

}
