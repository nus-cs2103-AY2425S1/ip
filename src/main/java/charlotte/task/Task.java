package charlotte.task;

/**
 * Represents a task with a description and completion status.
 * This is an abstract class that other specific types of tasks (e.g., ToDo, Deadline, Event)
 * will extend from.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task with the specified description.
     * The task is initially marked as not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task.
     * If the task is done, an "X" is returned; otherwise, a space is returned.
     *
     * @return A string representing the status of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); //mark done task with X
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Unmarks the task as done, setting its status back to not done.
     */
    public void unmark() {
        this.isDone = false;
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
     * Converts the task to a suitable format for saving to a file.
     * This method should be implemented by subclasses to ensure that
     * task-specific details are included in the file format.
     *
     * @return A string representing the task in a file format.
     */
    public abstract String toFileFormat();

    /**
     * Returns a string representation of the task.
     * The string includes the task's status icon and its description.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}
