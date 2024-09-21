package myapp.task;

import java.time.LocalDateTime;

import myapp.core.DateTimeHandler;

/**
 * The {@code Deadline} class represents a task that has a deadline.
 * It extends the {@link Task} class and includes a {@link LocalDateTime}
 * field representing the date and time by which the task must be completed.
 */
public class Deadline extends Task {

    protected LocalDateTime deadlineTime;

    /**
     * Constructs a {@code Deadline} object with the specified description and deadline date/time.
     *
     * @param description the description of the task.
     * @param by the {@link LocalDateTime} by which the task must be completed.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.deadlineTime = by;
    }

    /**
     * Returns the deadline of the task.
     *
     * @return the {@link LocalDateTime} by which the task must be completed.
     */
    public LocalDateTime getDateTime() {
        return deadlineTime;
    }

    /**
     * Returns a string representation of the {@code Deadline} object, including its type, description, and deadline.
     *
     * @return a string representation of the {@code Deadline} object.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + DateTimeHandler.format(deadlineTime) + ")";
    }

    /**
     * Returns a string formatted for saving to a file, including the task type,
     * completion status, description, and deadline.
     *
     * @return a string representation of the {@code Deadline} object formatted for file storage.
     */
    @Override
    public String toFileFormat() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | "
                + DateTimeHandler.format(deadlineTime, true);
    }
}
