package lebron;

import java.time.LocalDate;

/**
 * Represents a task with a description and a completion status.
 * This class provides basic methods to manipulate and retrieve task information,
 * such as marking a task as done or undone, and converting the task to a string format.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task object with the specified description.
     * The task is initially marked as not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Reschedules the task to a new date. This method is meant to be overridden by subclasses that
     * support rescheduling. The default implementation does nothing.
     *
     * @param newDate The new date to which the task should be rescheduled.
     */
    public void reschedule(LocalDate newDate) {
        // Default implementation does nothing.
    }

    /**
     * Returns the status icon of the task. "X" if the task is done, " " (a space) if not.
     *
     * @return The status icon of the task.
     */
    public String getStatusIcons() {
        return isDone ? "X" : " ";
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
     * Returns the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the completion status of the task.
     *
     * @return True if the task is done, false otherwise.
     */
    public boolean getStatus() {
        return this.isDone;
    }

    /**
     * Returns a string representation of the task, including its status icon and description.
     *
     * @return A string representing the task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcons(), this.description);
    }

    /**
     * Returns a string representation of the task formatted for file storage.
     * This method is meant to be overridden by subclasses to provide specific formats.
     *
     * @return An empty string by default.
     */
    public String toFileString() {
        return "";
    }
}
