package bobby.tasks;

import java.time.LocalDate;

/**
 * Represents a task with a description and a completion status.
 * The task can be marked as done or undone and can provide
 * formatted string representations for displaying or saving to a file.
 */
public class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructs a new Task with the given description.
     * The task is initially marked as not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon representing whether the task is done.
     *
     * @return "X" if the task is done, otherwise " ".
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
    public void markTask() {
        this.isDone = true;
    }

    /**
     * Unmarks the task, setting it as not done.
     */
    public void unmarkTask() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task, including its status icon
     * and description, formatted for display.
     *
     * @return A string in the format "[statusIcon] description".
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }

    /**
     * Returns a string representation of the task formatted for saving to a file.
     * The format is "| isDone | description".
     *
     * @return A string formatted for file storage.
     */
    public String toFileString() {
        return String.format("| %b | %s", isDone, this.description);
    }

    /**
     * Checks if the task is associated with the given date.
     * Default implementation returns false as a generic task does not have a date.
     *
     * @param date The date to check.
     * @return False, as a generic task does not have a date.
     */
    public boolean isOnDate(LocalDate date) {
        return false;
    }

}
