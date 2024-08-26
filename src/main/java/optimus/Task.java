package optimus;

/**
 * Abstract class representing a optimus.Task in the Optimus task management system.
 * This class is extended by specific task types such as Todo, Deadline, and Event.
 */
public abstract class Task {
    protected String description; // Description of the task
    protected boolean isDone; // optimus.Task completion status

    /**
     * Constructor to initialize a new optimus.Task with a description.
     * By default, the task is not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon representing whether the task is done or not.
     * 'X' represents a completed task, while a blank space represents an incomplete task.
     *
     * @return A string representing the task's status.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns a string representation of the task.
     *
     * @return A formatted string showing the task's status and description.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Abstract method to convert the task into a specific file format for saving.
     * This method is to be implemented by subclasses.
     *
     * @return A string in the format suitable for writing to a file.
     */
    public abstract String toFileFormat();

    public String getDescription() {
        return this.description;
    }
}
