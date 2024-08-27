package max.task;

/**
 * The Task class represents a task with a description and a status indicating whether it is done.
 * This class serves as the base class for more specific types of tasks, such as ToDo, Deadline, and Event.
 */
public class Task {
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
     * An "X" indicates that the task is done, while a space indicates that it is not done.
     *
     * @return A string representing the task's status icon.
     */
    public String getStatusIcon() {

        return (isDone ? "X" : " "); // mark done task with X
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
     * Marks the task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markNotDone() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task, including its status and description.
     *
     * @return A string in the format "[status] description", where the status is either "X" (done) or " " (not done).
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Converts the task to a string format suitable for saving to a file.
     *
     * @return A string in the format "X | status | description", where the status is either 1 (done) or 0 (not done).
     */
    public String toFileFormat() {
        return String.format("X | %d | %s", isDone ? 1 : 0, description);
    }

}
