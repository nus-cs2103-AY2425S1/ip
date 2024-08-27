package tasks;

/**
 * Represents a task.
 * Encapsulates the description of a task.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks a task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Unmarks the task.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Returns the status of the task in a string.
     *
     * @return status icon.
     */
    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    /**
     * Returns the string of the task to be saved to data file.
     *
     * @return string representing the task information.
     */
    public String writeTask() {
        StringBuilder s = new StringBuilder();
        s.append(this.isDone ? 1 : 0).append(",").append(this.description);
        return s.toString();
    }

    /**
     * Returns description of the task.
     *
     * @return description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the string representation of the task.
     *
     * @return string of task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), this.description);
    }
}
