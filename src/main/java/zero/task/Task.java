package zero.task;

/**
 * The {@code Task} class represents a task with a description and a completion status.
 * It provides methods to mark the task as done or not done, retrieve the task's status,
 * and format the task's details for display or storage.
 * Mainly used as a super class for other tasks with more information.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a {@code Task} with the specified description. The task is initially marked as not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task, an "X" if the task is done, or a space if it is not done.
     *
     * @return A string representing the task's status.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // Mark done tasks with X
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
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns "1" if the task is done, or "0" if it is not done.
     *
     * @return A string representing whether the task is done.
     */
    public String isDone() {
        return this.isDone ? "1" : "0";
    }

    /**
     * Formats the task's description for storage.
     *
     * @return A string representing the task in a format suitable for file storage.
     */
    public String toFormatted() {
        return this.description;
    }

    /**
     * Returns the description of the task.
     *
     * @return The task's description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns a string representation of the task, including its status icon and description.
     *
     * @return A string in the format "[status] description".
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
