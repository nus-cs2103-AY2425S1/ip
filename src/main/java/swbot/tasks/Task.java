package swbot.tasks;

/**
 * An abstract class that has methods that the inherited classes use.
 * Event, Deadline and Todo are types of tasks that inherit from this
 * class.
 */
public abstract class Task {
    private String description;
    private boolean isDone;

    /**
     * Creates a task object that contains the description of the task itself
     *
     * @param description description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status of the task (done/not done)
     *
     * @return a string that represents if the task is done or not
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Sets the status of the task to either done or not done
     *
     * @param setter true or false based on what status to set for the task
     */
    public void setDone(boolean setter) {
        this.isDone = setter;
    }

    /**
     * Converts the task object to a string format suitable for file storage.
     *
     * @return a string representing the task in a format that can be saved to a file
     */
    public abstract String toFileFormat();

    /**
     * Retrieves the description of the task.
     *
     * @return a string representing the description of the task
     */
    public String getDescription() {
        return description;
    }

    /**
     * Indicates whether the task is completed.
     *
     * @return true if the task is completed, false otherwise.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns a string representing the task along with its status
     *
     * @return string that shows if the task is done or not along with its description
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
