public class Task {
    private final String description;
    private boolean isDone;

    /**
     * Constructor that creates a Task object
     *
     * @param description that describes what the task is
     */
    public Task (String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the string representation of whether the task is done or not
     *
     * @return the string representation of the task status
     */
    private String getStatusIcon() {
        return (this.isDone ? "[X]" : "[ ]");
    }

    /**
     * String representation of the task
     *
     * @return the string representation of the task
     */
    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }

    /**
     * Mark the task as done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Mark the task as not done
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Get the task status
     *
     * @return true if the task is done and false if the task is not done
     */
    public boolean isDone() {
        return this.isDone;
    }
}
