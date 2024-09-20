package seedu.task;

/**
 * An abstract class that represents a generic task.
 * It contains the essential attributes and methods common to all types of tasks, such as
 * description and completion status.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a {@code Task} with the specified description and initializes the task as not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon representing whether the task is completed.
     * A completed task is represented by "X", while an incomplete task is represented by an empty space.
     *
     * @return The status icon as a string ("X" if done, " " if not done).
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
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
     * Returns the description of the task.
     *
     * @return The task description as a string.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Converts the {@code Task} object into a string representation for display purposes.
     * The format includes the task's status icon and description.
     *
     * @return A formatted string representing the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }

    /**
     * Converts the {@code Task} object into a string format suitable for saving in a file.
     * This method is abstract and should be implemented by subclasses to define their specific format.
     *
     * @return A string representing the task to be saved in a file.
     */
    public abstract String toSave();

    /**
     * Compares this {@code Task} with the specified object for equality.
     * This method is abstract and should be implemented by subclasses to define their specific comparison logic.
     *
     * @param obj The object to compare this {@code Task} with.
     * @return {@code true} if the specified object is equal to this task, {@code false} otherwise.
     */
    @Override
    public abstract boolean equals(Object obj);
}
