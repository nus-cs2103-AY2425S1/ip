package hien.task;

/**
 * Represents a task with a description and a status indicating whether it is done.
 * The {@code Task} class provides methods to mark the task as done or undone,
 * and to retrieve its description and status.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a {@code Task} with the specified description.
     * The task is initialized as not done.
     *
     * @param description A string describing the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a status icon representing whether the task is done.
     *
     * @return "X" if the task is done, otherwise a blank space.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as done by setting its status to true.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks the task as not done by setting its status to false.
     */
    public void markAsUndone() {
        isDone = false;
    }

    /**
     * Retrieves the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Checks if the task is marked as done.
     *
     * @return {@code true} if the task is done, {@code false} otherwise.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns a string representation of the task, including its status and description.
     *
     * @return A string in the format "[status] description", where status is either "X" (done) or a blank space.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
