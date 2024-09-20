package wenjiebot.tasks;

import wenjiebot.exceptions.InvalidSnoozeFormatException;
import wenjiebot.exceptions.SnoozeOnTodoException;

/**
 * The Task class represents a general task in the wenjiebot application.
 * It includes information about the task's description and completion status.
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
     * Sets the date and time for the event using the provided string.
     * The input must contain both the start time ("/from") and end time ("/to").
     *
     * @param newDate A string containing the new start and end times in the format "/from d/M/yyyy HHmm /to d/M/yyyy HHmm".
     * @throws InvalidSnoozeFormatException if the input format is invalid or cannot be parsed.
     */
    public abstract void setDateTime(String newDate) throws InvalidSnoozeFormatException, SnoozeOnTodoException;

    /**
     * Returns the status icon of the task.
     * The icon is "X" if the task is done, and a blank space if not done.
     *
     * @return A string representing the status icon of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Sets the completion status of the task.
     *
     * @param newStatus The new completion status of the task.
     */
    public void setStatusIcon(boolean newStatus) {
        this.isDone = newStatus;
    }

    /**
     * Returns whether the task is done.
     *
     * @return true if the task is done, false otherwise.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns a string representation of the Task in a format suitable for storage.
     * The format includes the completion status and description.
     *
     * @return A string representation of the Task for storage.
     */
    public String toPrettierString() {
        if (isDone) {
            return " | 1 | " + this.description;
        } else {
            return " | 0 | " + this.description;
        }
    }

    /**
     * Returns the description of the task.
     * This method provides access to the textual description that defines the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns a string representation of the Task for display purposes.
     * The format includes a status indicator and the description.
     *
     * @return A string representation of the Task for display.
     */

    @Override
    public String toString() {
        if (isDone) {
            return "[X] " + this.description;
        } else {
            return "[ ] " + this.description;
        }
    }
}
