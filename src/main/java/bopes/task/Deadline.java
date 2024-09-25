package bopes.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import bopes.exception.BopesException;

/**
 * The Deadline class represents a task with a specific deadline.
 * It extends the Task class and includes a LocalDateTime field to store the deadline date and time.
 */
public class Deadline extends Task {
    protected LocalDateTime by;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm a");

    /**
     * Constructs a new Deadline task with the specified description, deadline, and completion status.
     *
     * @param description the description of the task
     * @param by          the deadline of the task in the format "dd/MM/yyyy hh:mm a"
     * @param isDone      the completion status of the task
     * @throws BopesException if the deadline format is invalid
     */
    public Deadline(String description, String by, boolean isDone) throws BopesException {
        super(description, isDone);
        assert description != null && !description.isEmpty() : "Description cannot be null or empty.";
        try {
            if (by.trim().length() == 10) {
                by = by + " 12:00 am";
            }
            this.by = LocalDateTime.parse(by.toLowerCase(), formatter);
            assert this.by != null : "Parsed Deadline date cannot be null.";
        } catch (DateTimeParseException e) {
            throw new BopesException("Error: Invalid date/time format. Please use the format 'dd/MM/yyyy hh:mm a'.");
        }
    }

    /**
     * Returns a string representation of the Deadline task, including its type, description,
     * and formatted deadline.
     *
     * @return a string representation of the Deadline task
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(formatter) + ")";
    }

    /**
     * Returns a string representation of the Deadline task formatted for file storage.
     * The format includes the task type, completion status, description, and deadline.
     *
     * @return a string representation of the Deadline task in a file-friendly format
     */
    @Override
    public String toFileFormat() {
        return "D | " + (isDone ? "1" : "0") + " | " + this.description + " | " + this.by.format(formatter);
    }

    /**
     * Returns the deadline date and time of the task.
     *
     * @return the deadline date and time
     */
    public LocalDateTime getDateTime() {
        return this.by;
    }
}
