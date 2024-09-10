package mira;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a Deadline task that needs to be completed by a specific date/time.
 */
public class Deadline extends Task {
    private static final DateTimeFormatter DEFAULT_FORMAT = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private final LocalDateTime by; // The deadline for the task

    /**
     * Constructs a new Deadline task with the specified description and deadline.
     *
     * @param description The description of the Deadline task.
     * @param by          The deadline by which the task must be completed.
     */
    public Deadline(String description, String by) throws DateTimeParseException {
        super(description);
        this.by = LocalDateTime.parse(by, DEFAULT_FORMAT);
    }

    /**
     * Constructs a new Deadline task with the specified description, deadline and isDone.
     *
     * @param description The description of the Deadline task.
     * @param by          The deadline by which the task must be completed.
     * @param isDone      The status of the deadline.
     */
    public Deadline(String description, String by, boolean isDone) throws DateTimeParseException {
        super(description, isDone);
        this.by = LocalDateTime.parse(by, DEFAULT_FORMAT);
    }

    /**
     * Returns a string format of Deadline task to be saved for storage.
     *
     * @return A specified format string for storage.
     */
    @Override
    public String toFileString() {
        return "D | " + super.toFileString() + " | " + this.by.format(DEFAULT_FORMAT);
    }

    /**
     * Returns a string representation of the Deadline task.
     * It includes its type, status icon, description, and deadline.
     *
     * @return The task's type "[D]", status icon, description, and deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by.format(DateTimeFormatter.ofPattern("dd MMM yyyy, h:mma"))
                + ")";
    }
}
