package task;

import java.time.LocalDate;

/**
 * A generic task stored by BotManager.
 *
 * @author dwsc37
 */
public abstract class Task {
    private final String description;
    private boolean isDone = false;

    /**
     * Constructor for a <code>Task</code>.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
    }

    /**
     * Constructor for a <code>Task</code>, with <code>isDone</code> included.
     *
     * @param description Description of the task.
     * @param isDone Completion status of the task.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Mark the task as done.
     */
    protected void mark() {
        isDone = true;
    }

    /**
     * Mark the task as undone.
     */
    protected void unmark() {
        isDone = false;
    }

    private String getStatusIcon() {
        return isDone ? "[X]" : "[ ]";
    }

    /**
     * Returns the string representation of the task.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }

    /**
     * returns the data representation of the task, for storing in the data file.
     *
     * @return Data representation of the task.
     */
    public String toData() {
        return description + " | " + isDone;
    }

    /**
     * Checks if the task description matches the given search string.
     *
     * @param searchString Search string to compare.
     * @return True if the task description contains the search string, false otherwise.
     */
    protected boolean isMatch(String searchString) {
        return description.toLowerCase().contains(searchString.toLowerCase());
    }

    /**
     * Checks if the task falls on a date.
     *
     * @param date Date to check
     * @return True if the task falls on the date, false otherwise.
     */
    protected abstract boolean isOnDate(LocalDate date);
}
