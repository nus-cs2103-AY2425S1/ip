package dave.task;


/**
 * Represents a task that has a description and a completion status.
 * This is an abstract class and is meant to be extended by specific task types.
 */
public abstract class Task {

    /** The description of the task */
    private String description;

    /** The status of whether the task is done or not */
    private boolean isDone;

    /**
     * Constructs a Task with the given description. The task is initially marked as not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        assert description != null && !description.trim().isEmpty() : "Task description should not be null or empty";
        this.description = description;
        this.isDone = false;
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
     * Retrieves the current status of the task.
     *
     * @return "X" if the task is done, otherwise a blank space.
     */
    public String getStatus() {
        return (isDone ? "X" : " ");
    }

    /**
     * Converts the task into a string format suitable for saving to a file.
     * This is an abstract method that must be implemented by subclasses.
     *
     * @return A string representation of the task for saving.
     */
    public abstract String write();

    /**
     * Returns the status of the task indicating if it is done or not.
     *
     * @return {@code true} if the task is marked as done, {@code false} otherwise.
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Returns the description of the task.
     *
     * @return The description of the task as a {@code String}.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the string representation of the task for display purposes.
     *
     * @return A formatted string showing the status and description of the task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", getStatus(), description);
    }
}
