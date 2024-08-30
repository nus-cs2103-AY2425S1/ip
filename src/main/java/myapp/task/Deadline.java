package myapp.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The {@code Deadline} class represents a task that has a deadline.
 * It extends the {@code Task} class and includes additional information
 * about the date and time by which the task must be completed.
 */
public class Deadline extends Task {
    private LocalDateTime by;

    /**
     * Constructs a new {@code Deadline} task with the specified description
     * and deadline date and time.
     *
     * @param description A {@code String} describing the task.
     * @param by          A {@code LocalDateTime} object representing the deadline.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the deadline date and time for this task.
     *
     * @return A {@code LocalDateTime} object representing the deadline.
     */
    public LocalDateTime getBy() {
        return this.by;
    }

    /**
     * Returns a {@code String} representation of the deadline task.
     * The string includes the type of task, description, and formatted deadline.
     * If the deadline is invalid, it shows "Invalid Date".
     *
     * @return A {@code String} representation of this deadline task.
     */
    @Override
    public String toString() {
        if (this.by == null) {
            return "[D]" + super.toString() + " (by: Invalid Date)";
        }
        return "[D]" + super.toString() + " (by: " + this.by.format(DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a")) + ")";
    }
}
