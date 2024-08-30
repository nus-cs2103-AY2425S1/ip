package shrimp.task;

/**
 * Represents an abstract task with a description and a completion status.
 * Subclasses of {@code Task} must implement methods to mark the task as done or not done, and to return the type of the task.
 */
public abstract class Task {
    private final String description; //description of the task
    private final Boolean isDone; //boolean value for whether it's done or not

    /**
     * Constructs a {@code Task} with the specified description and completion status.
     *
     * @param description The description of the task.
     * @param isDone      The completion status of the task.
     */
    Task(String description, Boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Retrieves a string representation of the task's completion status.
     *
     * @return "X" if the task is done, otherwise " ".
     */
    private String getIsDone() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns the description of the task.
     *
     * @return The task description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns whether the task is completed.
     *
     * @return {@code true} if the task is done, {@code false} otherwise.
     */
    public Boolean isDone() {
        return isDone;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return A string in the format "[status] description".
     */
    @Override
    public String toString() {
        return "[" + getIsDone() + "] " + description;
    }

    /**
     * Marks this task as done and returns a new instance with the updated status.
     *
     * @return A new {@code Task} instance marked as done.
     */
    public abstract Task markAsDone();

    /**
     * Marks this task as not done and returns a new instance with the updated status.
     *
     * @return A new {@code Task} instance marked as not done.
     */
    public abstract Task markAsNotDone();

    /**
     * Returns the type of the task.
     *
     * @return A string representing the type of the task.
     */
    public abstract String getType();
}
