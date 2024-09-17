package Tasks;

/**
 * Represents a task with a description and a status indicating if it is done.
 * A Task can be marked as done and can provide its status in different formats.
 */
public class Task {
    /**
     * The description of the task.
     */
    protected String description;

    /**
     * The status of the task indicating if it is done.
     */
    protected boolean isDone;

    /**
     * Constructs a Task with the specified description.
     * The task is initially not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks the task as done by setting the isDone status to true.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Returns the status icon of the task.
     * If the task is done, an "X" is returned, otherwise a space " " is returned.
     *
     * @return A string representing the task's status icon.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns a string representation of the task.
     * The string includes the status icon and the task description.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }

    /**
     * Returns the description of the task.
     *
     * @return The task's description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns a string representing the task in a file-friendly format.
     * The format is: task type | status (1 for done, 0 for not done) | description.
     * The task type is "T" for ToDo, "D" for Deadline, or "E" for Event.
     *
     * @return A string representing the task in file format.
     */
    public String toFileFormat() {
        return (this instanceof ToDo ? "T" :
                this instanceof Deadline ? "D" : "E") +
                " | " + (isDone ? "1" : "0") + " | " + description;
    }
}
