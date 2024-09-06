package Noisy;

/**
 * Represents a task with a description and a status indicating whether it is done or not.
 * This is an abstract class and should be extended by specific types of tasks like Todo, Deadline, and Event.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Formats the task in a specific format for saving to a file.
     * This method must be implemented by subclasses to define how each task type is formatted.
     *
     * @return The formatted string representing the task.
     */
     public abstract String formatTask();

    /**
     * Marks the task as done.
     */

    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markUndone() {
        this.isDone = false;
    }

    /**
     * Returns the status icon representing the task's status.
     * The icon is "X" if the task is done, otherwise a space.
     *
     * @return The status icon of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns the string representation of the task.
     * The format includes the status icon and the task description.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}