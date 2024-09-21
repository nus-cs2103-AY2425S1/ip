package meep.task;

/**
 * The {@code Task} class represents a task with a description and a status indicating whether it is done.
 * It provides methods to mark the task as done or undone,
 * and to retrieve the task's status and formatted representations.
 */
public class Task {
    private final String description;
    private boolean isDone;

    /**
     * Constructs a new {@code Task} with the specified description.
     * By default, the task is initially marked as not done.
     *
     * @param description A description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
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
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Returns the status icon representing whether the task is done or not.
     *
     * @return "X" if the task is done, otherwise " ".
     */
    private String getStatusIcon() {
        return (this.isDone ? "X" : " ");
    }

    /**
     * Returns a string that represents the task in a format suitable for saving to a file.
     * The format is "1|description" if the task is done, and "0|description" if not.
     *
     * @return The save format string of the task.
     */
    public String getSaveFormat() {
        return (this.isDone ? "1" : "0") + "|" + this.description;
    }

    /**
     * Returns a string representation of the task.
     * The format is "[status] description", where "status" is an "X" if the task is done, and " " if not.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
