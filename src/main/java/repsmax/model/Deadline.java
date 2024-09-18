package repsmax.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a deadline.
 * <p>
 * A {@code Deadline} object corresponds to a task with a specific due date/time.
 * The task has a description, priority level, and a deadline indicating when the task is due.
 * </p>
 */
public class Deadline extends Task {
    private LocalDateTime by;

    /**
     * Constructs a {@code Deadline} task with the specified description, due date/time, and priority.
     *
     * @param description The description of the deadline task.
     * @param by          The {@code LocalDateTime} object representing the due date and time of the task.
     * @param priority    The priority level of the task (1 = high, 2 = medium, 3 = low).
     */
    public Deadline(String description, String by, int priority) {
        super(description, priority);
        this.by = parseDateTime(by);

        if (this.by == null) {
            throw new IllegalArgumentException("Invalid date format. Use 'yyyy-MM-dd HHmm'.");
        }
    }

    /**
     * Returns a string representation of the deadline task.
     * <p>
     * The format is "[D][statusIcon] [priority] description (by: MMM dd yyyy HHmm)", where "[D]" indicates
     * that the task is a deadline, followed by the task's priority and due date/time formatted as "MMM dd yyyy HHmm".
     * </p>
     *
     * @return A string representation of the deadline task.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
        return "[D]" + super.toString() + " (by: " + by.format(formatter) + ")";
    }

    /**
     * Parses a string representing a date and time into a {@code LocalDateTime} object.
     * <p>
     * The expected format is {@code yyyy-MM-dd HHmm}. If the string cannot be parsed, the method returns {@code null}.
     * </p>
     *
     * @param dateTime The string representing the date/time to be parsed.
     * @return The {@code LocalDateTime} object representing the parsed date/time, or {@code null} if parsing fails.
     */
    private LocalDateTime parseDateTime(String dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        try {
            return LocalDateTime.parse(dateTime, formatter);
        } catch (DateTimeParseException e) {
            // Return null if parsing fails
            return null;
        }
    }
}

