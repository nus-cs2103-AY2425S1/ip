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
        assert by != null && !by.trim().isEmpty() : "Deadline cannot be null or empty.";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm a");
        try {
            if (by.trim().length() == 10) {  // Length of "dd/MM/yyyy" is 10
                by = by + " 12:00 am";  // Default to midnight if time is not provided
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
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a");
        return "[D]" + super.toString() + " (by: " + by.format(outputFormat) + ")";
    }

    /**
     * Returns a string representation of the Deadline task formatted for file storage.
     * The format includes the task type, completion status, description, and deadline.
     *
     * @return a string representation of the Deadline task in a file-friendly format
     */
    @Override
    public String toFileFormat() {
        DateTimeFormatter fileFormat = DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a");
        return "D | " + (isDone ? "1" : "0") + " | " + this.description + " | " + this.by.format(fileFormat);
    }
}
