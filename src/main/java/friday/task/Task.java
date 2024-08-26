package friday.task;

/**
 * Represents a task with a description and a status indicating whether it is completed or not.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new Task with the specified description. The task is initially not done.
     *
     * @param description A description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon representing whether the task is done.
     *
     * @return A string representing the status of the task ("X" if done, " " otherwise).
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void unmarkAsDone() {
        isDone = false;
    }

    /**
     * Returns a string representing the task in a format suitable for file storage.
     *
     * @return A string representing the task in file format.
     */
    public String toFileFormat() {
        return "| " + (isDone ? "1" : "0") + " | " + description;
    }

    /**
     * Returns a string representing the task in a human-readable format.
     *
     * @return A string representing the task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + description;
    }

    /**
     * Checks if the task is done.
     *
     * @return True if the task is done, false otherwise.
     */
    public boolean isDone() {
        return isDone;
    }
}
