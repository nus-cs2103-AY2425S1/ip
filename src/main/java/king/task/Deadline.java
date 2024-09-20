package king.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Constructs a Deadline task with the given description and deadline time.
     *
     * @param description A brief description of the task.
     * @param by The deadline for the task.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Constructs a Deadline task with the given description, deadline time, and completion status.
     *
     * @param description A brief description of the task.
     * @param by The deadline for the task.
     * @param isDone The completion status of the task.
     */
    public Deadline(String description, LocalDateTime by, boolean isDone) {
        super(description);
        this.by = by;
        this.isDone = isDone;
    }

    /**
     * Returns a string representation of the deadline task.
     *
     * @return A string representing the task's status, description, and deadline.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        String formattedBy = by.format(formatter);
        return "[D]" + "[" + this.getStatusIcon() + "]" + " " + this.description + " (by: " + formattedBy + ")";
    }

    /**
     * Returns a string representation of the deadline task suitable for saving to a file.
     *
     * @return A string representing the task in a file format.
     */
    @Override
    public String toFileString() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by;
    }

    @Override
    public LocalDateTime getDueDateTime() {
        return this.by;
    }
}
