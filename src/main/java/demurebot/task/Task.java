package demurebot.task;

/**
 * Abstract class representing a task in the DemureBot application.
 * A task has a description and a status indicating whether it is done.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new Task with the specified description and status.
     *
     * @param description The description of the task.
     * @param isDone The status of the task, true if the task is done, false otherwise.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns the status icon of the task.
     * "X" if the task is done, " " otherwise.
     *
     * @return The status icon of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Unmarks the task, setting its status to not done.
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
     * Returns a string representation of the task.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + description;
    }
}

