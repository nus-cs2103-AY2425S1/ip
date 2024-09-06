package tasks;

/**
 * Represents an abstract task with a description and completion status.
 */
public abstract class Task {
    protected final String description;
    protected boolean isDone;

    /**
     * Constructs a new Task with the given description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks the task as done.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Unmarks the task as not done.
     */
    public void unMark() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return A string describing the task.
     */
    @Override
    public String toString() {
        String check = isDone ? "[X]" : "[ ]";
        return " " + check + " " + this.description;
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
     * Checks if the task is due within the next specified number of days.
     * This is a default implementation that does nothing for tasks without deadlines/events.
     *
     * @param days The number of days to check for upcoming deadlines.
     * @return false by default for tasks with no due date.
     */
    public boolean isDueWithinDays(int days) {
        return false;
    }

    /**
     * Returns the file format string of the task for storage.
     *
     * @return A string suitable for storing in a file.
     */
    public abstract String toFileFormat();
}
