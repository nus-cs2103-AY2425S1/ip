package tars;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;


/**
 * Represents a task with a deadline, including a due date.
 *
 * <p>The Deadline class extends the Task class by adding a due date to the task.
 * It uses the {@link DateTimeParser} class to parse and format date strings. The class provides
 * methods to get the due date, change the due date, and generate a string representation of the task.
 */
public class Deadline extends Task {

    private LocalDateTime dueDate;

    /**
     * Creates a new Deadline task with a specified name, completion status, and due date.
     * The date is parsed using the {@link DateTimeParser} class. If the date cannot be parsed,
     * a {@link TarsException} is thrown.
     *
     * @param name The name or description of the deadline task.
     * @param done Indicates whether the task is completed or not.
     * @param date The due date of the task in the format "yyyy-MM-dd HHmm" or "dd MMM yyyy, HH:mm".
     * @throws TarsException If the provided date cannot be parsed into a {@link java.time.LocalDateTime}.
     */
    public Deadline(String name, boolean done, String date) throws TarsException {
        super(name, done);
        this.dueDate = DateTimeParser.parseDateTimeString(date);
    }

    /**
     * Parses a given date string into a {@link LocalDateTime} object.
     *
     * <p>This method attempts to parse the date string using the {@link DateTimeParser} class.
     * If the string does not match the expected formats, a {@link TarsException} is thrown.
     * The accepted date formats are "yyyy-MM-dd HHmm" or "dd MMM yyyy, HH:mm".
     *
     * @param date the date string to be parsed, in either "yyyy-MM-dd HHmm" or "dd MMM yyyy, HH:mm" format.
     * @return the parsed {@link LocalDateTime} object.
     * @throws TarsException if the date string cannot be parsed into a valid {@link LocalDateTime} object.
     */
    private LocalDateTime parseDueDate(String date) throws TarsException {
        try {
            return DateTimeParser.parseDateTimeString(date);
        } catch (DateTimeParseException e) {
            throw new TarsException("Invalid date format. Please use the format: yyyy-MM-dd HHmm.");
        }
    }

    /**
     * Changes the due date of the deadline task.
     *
     * <p>The new date is parsed using the {@link DateTimeParser} class. If the new date cannot
     * be parsed, a {@link TarsException} is thrown.
     */
    public String getDate() {
        return DateTimeParser.formatDateTimeString(this.dueDate);
    }

    /**
     * Changes the due date of the deadline task.
     *
     * <p>The new date is parsed using the {@link DateTimeParser} class. If the new date cannot
     * be parsed, a {@link TarsException} is thrown.
     *
     * @param newDate the new due date in the format "yyyy-MM-dd HHmm" or "dd MMM yyyy, HH:mm".
     * @throws TarsException if the new date cannot be parsed into a {@link java.time.LocalDateTime}.
     */
    public void changeDate(String newDate) throws TarsException {
        this.dueDate = DateTimeParser.parseDateTimeString(newDate);
    }

    /**
     * Returns a string representation of this deadline task.
     * The format of the string is "[D] [task description] (by: [due date])".
     *
     * @return A string representation of the deadline task.
     */
    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + getDate() + ")";
    }
}

