package task;

/**
 * Represents a task with a description and a completion status.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    protected String type;
    protected int priority;

    /**
     * Initializes a Task with a description. The task is initially marked as not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.priority = -1; //default priority i.e. no priority set
    }

    /**
     * Returns the status icon of the task, which is "X" if the task is done, and " " if not.
     *
     * @return The status icon of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // Mark done task with X
    }

    /**
     * Returns the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Marks the task as done.
     */
    public void markTaskAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void unmarkTask() {
        this.isDone = false;
    }

    /**
     * Provides a string representation of the task suitable for saving to a file.
     * By default, returns "Undefined task."
     *
     * @return A string representation of the task for file storage.
     */
    public String toFile() {
        return "Undefined task.";
    }

    /**
     * Provides a string representation of the task for display.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
