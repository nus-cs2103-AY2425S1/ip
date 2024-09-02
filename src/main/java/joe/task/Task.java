package joe.task;

/**
 * The {@code Task} class is an abstract class that represents a task with a description
 * and a completion status. It provides methods for marking a task as done or not done,
 * checking if a task contains a query string, and converting the task to a string
 * representation. Subclasses should provide their own implementation of the
 * {@code serialize} method.
 */
public abstract class Task {
    /** The description of the task. */
    protected String description;

    /** The completion status of the task. */
    protected boolean isDone;

    /**
     * Constructs a new {@code Task} with the specified description.
     * The task is initialized as not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs a new {@code Task} with the specified description and completion status.
     *
     * @param description The description of the task.
     * @param isDone The completion status of the task.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Serializes the task into a string format to be stored in a file.
     * This method should be implemented by subclasses.
     *
     * @return A formatted string representing the task.
     */
    public abstract String serialize();

    /**
     * Returns the status icon of the task.
     *
     * @return "X" if the task is done, otherwise " ".
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void unmarkDone() {
        this.isDone = false;
    }

    /**
     * Sets the task's completion status.
     *
     * @param isDone The new completion status of the task.
     * @return The updated {@code Task} object.
     */
    public Task setIsDone(boolean isDone) {
        this.isDone = isDone;
        return this;
    }

    /**
     * Checks if the task description contains the specified query string.
     *
     * @param query The query string to search for.
     * @return {@code true} if the description contains the query, {@code false} otherwise.
     */
    public boolean contains(String query) {
        return this.description.contains(query);
    }

    /**
     * Returns a string representation of the task for displaying to the user.
     * The format is "[statusIcon] description".
     *
     * @return A string representing the task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}
