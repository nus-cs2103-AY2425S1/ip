// Parent Task class
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a new Task with the specified description.
     *
     * @param description the task description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task.
     *
     * @return "X" if the task is done, otherwise " "
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as done or not done.
     *
     * @param status the status to set (true for done, false for not done)
     */
    public void markAsDone(boolean status) {
        this.isDone = status;
    }

    /**
     * Returns the string representation of the task.
     *
     * @return the string representation of the task with its status
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}