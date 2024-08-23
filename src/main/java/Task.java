public class Task {
    private final String description;
    private boolean isDone;

    /**
     * Constructs a Task with the specified description
     * The task is initialized with the given description and is marked as not done
     *
     * @param description The description of the task provided by the user
     */
    public Task (String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the description of this task
     *
     * @return The description of the task
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Checks if the task has been completed
     *
     * @return true if the task is completed, false otherwise
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Sets the completion status of the task
     *
     * @param isDone true to mark the task as completed, false to mark it as not completed
     */
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Returns the status icon representing the completion state of the task
     * Returns "X" if the task is completed, otherwise returns " "
     *
     * @return A string representing the status icon of the task
     */
    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }
}
