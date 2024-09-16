package bottleopener.task;

/**
 * Represents a task with a description and a status indicating whether it is completed.
 * <p>
 * This is an abstract class that defines the basic properties and methods for a task.
 * Subclasses must implement the methods to mark the task as done or undone,
 * retrieve the task type, and get the time associated with the task (if applicable).
 * </p>
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new Task with the specified description.
     * The task is initially marked as not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        assert description != null && !description.isEmpty() : "Description cannot be empty";
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs a new Task with the specified description and status.
     *
     * @param description The description of the task.
     * @param isDone      The completion status of the task; {@code true} if the task is done, {@code false} otherwise.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Marks the task as done and returns an updated Task object.
     *
     * @return A new Task object with status set to {@code true}.
     */
    public abstract Task markAsDone();

    /**
     * Marks the task as undone and returns an updated Task object.
     *
     * @return A new Task object with status set to {@code false}.
     */
    public abstract Task markAsUndone();

    /**
     * Returns the type of the task (e.g., T, D, E).
     *
     * @return The type of the task as a String.
     */
    public abstract String getType();

    /**
     * Returns the time associated with the task, if applicable.
     *
     * @return The time associated with the task as a String.
     */
    public abstract String getTime();

    /**
     * Returns the completion status of the task.
     *
     * @return {@code true} if the task is marked as done, {@code false} otherwise.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Returns the status icon of the task.
     *
     * @return "X" if the task is done, otherwise a space (" ").
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
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
     * Returns a string representation of the task, including its type, status icon, and description.
     *
     * @return A string in the format "[type] [status icon] description".
     */
    @Override
    public String toString() {
        assert !this.getType().isEmpty() : "Must have a type";
        assert this.getStatusIcon() != null : "Must have a status icon";
        return String.format("[%s] [%s] %s", this.getType(), this.getStatusIcon(), this.description);
    }

}
