package task;

/**
 * Represents a task with a description and a completion status.
 * This is an abstract class that serves as a base for specific types of tasks.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task with the specified description.
     * The task is initially marked as not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the status of the task.
     * A "[X]" indicates that the task is done, while "[ ]" indicates that it is not done.
     *
     * @return The status icon of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Checks if task is done.
     *
     * @return True if task is done, false otherwise.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Returns the string representation of the task.
     * Includes status icon and task description.
     *
     * @return A strign representation of the task
     */
    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }

    /**
     * Returns a formatted string suitable for saving the task to a file.
     * This method is abstract and must be implemented by subclasses.
     *
     * @return A formatted string representation of the task for saving.
     */
    public abstract String toSaveFormat();
}