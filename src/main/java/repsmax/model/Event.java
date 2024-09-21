package repsmax.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task that occurs within a specific time frame.
 * <p>
 * An {@code Event} object corresponds to a task with a start and end time.
 * It includes information about the description, priority, and the time range during which the event occurs.
 * </p>
 */
public class Event extends Task {

    /** The start date/time of the event. */
    private LocalDateTime from;

    /** The end date/time of the event. */
    private LocalDateTime to;

    /**
     * Constructs a new {@code Event} object with the specified description, start time, and end time.
     * <p>
     * The {@code from} and {@code to} parameters should be in the format {@code yyyy-MM-dd HHmm}.
     * </p>
     *
     * @param description The description of the event.
     * @param from        The start date/time of the event in the format {@code yyyy-MM-dd HHmm}.
     * @param to          The end date/time of the event in the format {@code yyyy-MM-dd HHmm}.
     * @param priority    The priority level of the event (1 = high, 2 = medium, 3 = low).
     * @throws IllegalArgumentException if the date format is invalid.
     */
    public Event(String description, String from, String to, int priority) {
        super(description, priority);
        this.from = parseDateTime(from);
        this.to = parseDateTime(to);

        if (this.from == null || this.to == null) {
            throw new IllegalArgumentException("Invalid date format. Use 'yyyy-MM-dd HHmm'.");
        }
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

    /**
     * Returns a string representation of the event, including its status, start, and end times.
     * <p>
     * The format is {@code "[E][statusIcon] [priority] description (from: MMM dd yyyy, HH:mm to: MMM dd yyyy, HH:mm)"}.
     * </p>
     *
     * @return A string representing the event.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm");
        String fromStr = (from != null) ? from.format(formatter) : "Invalid start time";
        String toStr = (to != null) ? to.format(formatter) : "Invalid end time";

        return "[E]" + super.toString() + " (from: " + fromStr + " to: " + toStr + ")";
    }

    /**
     * Returns a string in the file format used to save the event to disk.
     * <p>
     * The format is {@code "E | doneStatus | description | priority | from | to"}, where:
     * <ul>
     *   <li>{@code doneStatus} is "1" if the event is done, and "0" if it is not.</li>
     *   <li>{@code from} and {@code to} are in {@code yyyy-MM-ddTHHmm} format.</li>
     * </ul>
     * </p>
     *
     * @return A string representing the event in file format.
     */
    @Override
    public String toFileFormat() {
        return "E | " + (isDone() ? "1" : "0") + " | " + getDescription() + " | "
                + getPriority() + " | " + from + " | " + to;
    }
}
