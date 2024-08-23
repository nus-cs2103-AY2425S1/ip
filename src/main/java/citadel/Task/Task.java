package citadel.Task;

/**
 * Represents an abstract task in the Citadel application.
 * This class provides the common structure and behavior for all tasks.
 * Each task has a description and a status indicating whether it is done.
 */
public abstract class Task {

    /** The description of the task. */
    protected String description;

    /** The status of the task, indicating whether it is done. */
    protected boolean isDone;

    /**
     * Constructs a new Task with the specified description.
     * The task is initially not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task.
     * If the task is done, it returns "X", otherwise it returns " ".
     *
     * @return The status icon of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns a string representation of the task, including its status icon
     * and description.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void unMark() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task that can be used for
     * printing or saving to a file. This method must be implemented by
     * subclasses.
     *
     * @return A string representation of the task.
     */
    public abstract String printTask();
}
