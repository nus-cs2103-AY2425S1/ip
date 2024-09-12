package shrimp.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline.
 * A {@code Deadline} task has a description, a due date, and a completion status.
 */
public class Deadline extends Task {
    private final LocalDateTime deadline;

    /**
     * Constructs a {@code Deadline} task with the specified description, due date, and completion status.
     *
     * @param description The description of the task.
     * @param deadline    The date and time by which the task should be completed.
     * @param isDone      The completion status of the task.
     */
    public Deadline(String description, LocalDateTime deadline, boolean isDone) {
        super(description, isDone);
        this.deadline = deadline;
    }

    /**
     * Marks this task as done and returns a new {@code Deadline} instance with the updated status.
     *
     * @return A new {@code Deadline} instance marked as done.
     */
    @Override
    public Deadline markAsDone() {
        return new Deadline(getDescription(), this.deadline, true);
    }

    /**
     * Marks this task as not done and returns a new {@code Deadline} instance with the updated status.
     *
     * @return A new {@code Deadline} instance marked as not done.
     */
    @Override
    public Deadline markAsNotDone() {
        return new Deadline(getDescription(), this.deadline, false);
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
    public LocalDateTime getDeadline() {
        return deadline;
    }

    /**
     * Returns a string representation of the task, including the due date.
     *
     * @return A string in the format "[D][status] description (by: MMM dd yyyy)".
     */
    @Override
    public String toString() {
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return getType() + super.toString() + " (by: " + deadline.format(pattern) + ")";
    }
}
