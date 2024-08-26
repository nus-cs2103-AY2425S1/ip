package bopes.task;

/**
 * The Task class represents a generic task with a description and a completion status.
 * It serves as an abstract base class for specific types of tasks, such as ToDo, Deadline, and Event.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new Task with the specified description and completion status.
     *
     * @param description the description of the task
     * @param isDone      the initial completion status of the task
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns the status icon representing the completion status of the task.
     * 
     * @return "X" if the task is done, otherwise a space character
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as done by setting its completion status to true.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done by setting its completion status to false.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task, including its status icon and description.
     *
     * @return a string representation of the task
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Returns a string representation of the task formatted for file storage.
     * This method is abstract and must be implemented by subclasses.
     *
     * @return a string representation of the task in a file-friendly format
     */
    abstract public String toFileFormat();
}
