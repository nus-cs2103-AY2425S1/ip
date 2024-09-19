package rasputin.task;

/**
 * Represents a task to be completed. A task can be of 3 different types and can be marked as done or not done.
 *
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status of the task. If task is done, return an "X", else, return a " ".
     *
     * @return Status icon of task as a String.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns the status of task to be used in the file format. If task is done, return a "1", else return "0".
     *
     * @return Status identifier of task as a String.
     */
    public String getStatusIdentifier() {
        return (isDone ? "1" : "0");
    }

    /**
     * Returns the type of the task.
     *
     * @return Type of task.
     */
    public String getType() {
        return "rasputin";
    }

    /**
     * Returns the description of the task.
     *
     * @return Task description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns whether the task is done.
     *
     * @return Status of task.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        if (isDone) {
            throw new InvalidTaskException("ERROR! This task is already done.");
        }
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        if (!isDone) {
            throw new InvalidTaskException("ERROR! This task is already not done.");
        }
        this.isDone = false;
    }

    /**
     * Returns the string as the format to be saved in the .txt file.
     *
     * @return String in the format to be saved
     */
    public abstract String toSaveFormat();

    /**
     * Returns string representation of the task with the status icon followed by the description.
     *
     * @return String representation of task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
