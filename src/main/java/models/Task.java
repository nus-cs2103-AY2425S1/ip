package models;

/**
 * Represents a task in the task management system.
 * The {@code Task} class serves as a base class for various types of tasks,
 * such as {@code Todo}, {@code Event}, and {@code Deadline}.
 * Each task has a description and a status indicating whether it is completed.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a {@code Task} with the specified description.
     * The task is initialized as not completed.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs a {@code Task} with the specified description and completion status.
     *
     * @param description The description of the task.
     * @param isDone The completion status of the task; {@code true} if the task is completed, {@code false} otherwise.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns whether the task is completed.
     *
     * @return {@code true} if the task is completed, {@code false} otherwise.
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Returns the status icon representing whether the task is completed.
     * If the task is completed, returns "X", otherwise returns a space.
     *
     * @return A string representing the status of the task ("X" for done, " " for not done).
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
     * Marks the task as completed.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Unmarks the task, setting it as not completed.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Serializes the task into a string format for saving in the database.
     * The default implementation returns an empty string, meant to be overridden by subclasses.
     *
     * @return A serialized string representation of the task.
     */
    public String serialize() {
        return "";
    }

    /**
     * Returns a string representation of the task.
     * The format is "[<statusIcon>] <description>", where {@code statusIcon} is "X" if the task is completed or a space otherwise.
     *
     * @return A string representing the task for display.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.getDescription());
    }
}
