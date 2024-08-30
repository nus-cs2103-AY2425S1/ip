package shrimp.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline.
 * A {@code Deadline} task has a description, a due date, and a completion status.
 */
public class Deadline extends Task {
    private final LocalDateTime by;

    /**
     * Constructs a {@code Deadline} task with the specified description, due date, and completion status.
     *
     * @param description The description of the task.
     * @param by          The date and time by which the task should be completed.
     * @param isDone      The completion status of the task.
     */
    public Deadline(String description, LocalDateTime by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Marks this task as done and returns a new {@code Deadline} instance with the updated status.
     *
     * @return A new {@code Deadline} instance marked as done.
     */
    @Override
    public Deadline markAsDone() {
        return new Deadline(getDescription(), this.by, true);
    }

    /**
     * Marks this task as not done and returns a new {@code Deadline} instance with the updated status.
     *
     * @return A new {@code Deadline} instance marked as not done.
     */
    @Override
    public Deadline markAsNotDone() {
        return new Deadline(getDescription(), this.by, false);
    }

    /**
     * Returns the type of the task.
     *
     * @return A string representing the type of the task, "[D]" for Deadline.
     */
    public String getType() {
        return "[D]";
    }

    /**
     * Retrieves the date and time by which this task should be completed.
     *
     * @return The due date and time.
     */
    public LocalDateTime getBy() {
        return by;
    }

    /**
     * Returns a string representation of the task, including the due date.
     *
     * @return A string in the format "[D][status] description (by: MMM dd yyyy)".
     */
    @Override
    public String toString() {
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return getType() + super.toString() + " (by: " + by.format(pattern) + ")";
    }
}