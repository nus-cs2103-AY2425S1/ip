package elsa.task;

/**
 * Represents a task.
 * @author Aaron
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a task with the specified description and completion status.
     *
     * @param description A description of the task.
     * @param isDone True if the task is completed, false otherwise.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns the completion status of the task.
     *
     * @return True if the task is completed, false otherwise.
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Returns a status icon representing the completion status of the task.
     * An "X" is returned if the task is completed, otherwise a space " ".
     *
     * @return A string representing the status of the task.
     */
    public String getStatusIcon() {
        return isDone ? "X" : " "; // mark a done task with 'X'
    }

    /**
     * Gets the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Marks the task as completed.
     */
    public void done() {
        this.isDone = true;
    }

    /**
     * Marks the task as not completed.
     */
    public void notDone() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task, including its completion status and description.
     *
     * @return A string representing the task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
