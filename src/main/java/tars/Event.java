package tars;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * Represents an event task with a specific start and end time.
 *
 * <p>The Event class extends the Task class and adds start and end times to the task.
 * These times are stored as {@link LocalDateTime} objects and are formatted for display
 * and storage. The class also provides methods to set and get these times, and handles
 * parsing and formatting of date and time strings.
 */
public class Event extends Task {

    private static final String DATE_FORMAT_ERROR = "Invalid date format. Please use the format: yyyy-MM-dd HHmm.";
    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Creates a new Event task with a specified name, completion status, start time, and end time.
     * The start and end times are parsed using the {@link DateTimeParser} class. If either of the dates
     * cannot be parsed, a {@link TarsException} is thrown.
     *
     * @param name The name or description of the event task.
     * @param isDone Indicates whether the task is completed or not.
     * @param from The start time of the event in the format "yyyy-MM-dd HHmm" or "dd MMM yyyy, HH:mm".
     * @param to The end time of the event in the format "yyyy-MM-dd HHmm" or "dd MMM yyyy, HH:mm".
     * @throws TarsException If either the start or end time cannot be parsed into a {@link java.time.LocalDateTime}.
     */
    public Event(String name, boolean isDone, String from, String to) throws TarsException {
        super(name, isDone);
        this.from = parseDate(from);
        this.to = parseDate(to);
    }

    public void setFrom(String newFrom) throws TarsException {
        this.from = parseDate(newFrom);
    }

    public void setTo(String newTo) throws TarsException {
        this.to = parseDate(newTo);
    }

    /**
     * Parses a given date string into a {@link LocalDateTime} object.
     *
     * <p>This method attempts to parse the date string using predefined date-time formats.
     * If the string cannot be parsed, it throws a {@link TarsException} with a detailed error message.
     * The accepted formats are "yyyy-MM-dd HHmm" and "dd MMM yyyy, HH:mm".
     *
     * @param date the date string to be parsed, in either "yyyy-MM-dd HHmm" or "dd MMM yyyy, HH:mm" format.
     * @return the parsed {@link LocalDateTime} object.
     * @throws TarsException if the date string cannot be parsed into a valid {@link LocalDateTime} object.
     */
    private LocalDateTime parseDate(String date) throws TarsException {
        try {
            return DateTimeParser.parseDateTimeString(date);
        } catch (DateTimeParseException e) {
            throw new TarsException(DATE_FORMAT_ERROR);
        }
    }

    public String getFrom() {
        return DateTimeParser.formatDateTimeString(this.from);
    }

    public String getTo() {
        return DateTimeParser.formatDateTimeString(this.to);
    }

    /**
     * Returns a string representation of this event task.
     *
     * <p>The format of the string includes the task type "[E]" for Event, followed by the task description,
     * and the start and end times formatted using the {@link DateTimeParser} class.
     * The returned string follows this format:
     * "[E] {task description} (from: {start time} to: {end time})".
     *
     * @return a string representation of the event task, including its type, description, start time, and end time.
     */
    @Override
    public String toString() {
        return "[E] " + super.toString() + " (from: " + getFrom() + " to: " + getTo() + ")";
    }
}
