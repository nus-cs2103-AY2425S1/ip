package echoa.task;

import java.time.LocalDate;

/**
 * Task is an abstract class that encapsulates the characteristics of a Task.
 * It contains the field, description and isDone.
 */

public abstract class Task {
    protected String description;
    protected boolean isDone = false;

    public Task(String description) {
        this.description = description;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Reformat the given date into MONTH-DD-YYYY.
     *
     * @param date LocalDate to be reformatted.
     * @return String representation of reformatted date.
     */
    public static String getReformattedDate(LocalDate date) {
        return date.getMonth() + " " +
                date.getDayOfMonth() + " " +
                date.getYear();
    }

    public String getDescription() {
        return this.description;
    }

    public void editDescription(String newDescription) {
        this.description = newDescription;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Unmarks the task as not done.
     */
    public void unmarkAsUndone() {
        this.isDone = false;
    }

    /**
     * Converts the isDone status to an icon,
     * with X representing done.
     *
     * @return String representation of status icon.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public abstract void update(Object[] details);

    /**
     * Returns a String (file text) representation of the task.
     *
     * @return String representation of text.
     */
    public abstract String toText();

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }
}
