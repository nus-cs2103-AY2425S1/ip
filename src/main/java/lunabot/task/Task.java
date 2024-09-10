package lunabot.task;

/**
 * The Task class represents a generic task with a description and completion status.
 * It serves as a base class for more specific types of tasks such as ToDo, Deadline, and Event.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task object with a specified description and completion status.
     *
     * @param description The description of the task.
     * @param isDone      The completion status of the task.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns a status icon that represents whether the task is done.
     * 'X' for done, and an empty space for not done.
     *
     * @return A string representing the task's completion status.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Gets the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Marks the task as done by setting its completion status to true.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Unmarks the task as done by setting its completion status to false.
     */
    public void unmarkAsDone() {
        this.isDone = false;
    }

    /**
     * Converts the task to a file-friendly format.
     * This method must be implemented by subclasses to return the correct format for each specific task type.
     *
     * @return A string representing the task in a format suitable for file storage.
     */
    public abstract String toFileFormat();

    /**
     * Returns a string representation of the task, including its completion status and description.
     *
     * @return A string representing the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
