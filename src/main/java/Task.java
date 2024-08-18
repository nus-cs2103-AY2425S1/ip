public class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructor for the Task class.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task.
     *
     * @return "X" if the task is done, " " otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task to be done or not done.
     *
     * @param status of the task
     */
    public void markAsDone(boolean status) {
        this.isDone = status;
    }

    /**
     * Returns the description of the task.
     *
     * @return The description of the task.
     */
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}