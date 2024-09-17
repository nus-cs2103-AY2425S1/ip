package repsmax.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task that occurs within a specific time frame.
 * An <code>Event</code> object corresponds to a task with a start and end time.
 */
public class Event extends Task {

    /** The start date/time of the event. */
    private LocalDateTime from;

    /** The end date/time of the event. */
    private LocalDateTime to;

    /**
     * Constructs a new Event object with the specified description, start time, and end time.
     *
     * @param description The description of the event.
     * @param from The start date/time of the event.
     * @param to The end date/time of the event.
     */
    public Event(String description, String from, String to, int priority) {
        super(description, priority);
        this.from = parseDateTime(from);
        this.to = parseDateTime(to);

        if (this.from == null || this.to == null) {
            throw new IllegalArgumentException("Invalid date format. Use 'yyyy-MM-dd HHmm'.");
        }
    }

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
     * Returns a string representation of the Event object, including its status, start, and end times.
     *
     * @return A string representing the Event object.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm");
        // Check for null values before formatting
        String fromStr = (from != null) ? from.format(formatter) : "Invalid start time";
        String toStr = (to != null) ? to.format(formatter) : "Invalid end time";

        return "[E]" + super.toString() + " (from: " + fromStr + " to: " + toStr + ")";
    }

    /**
     * Returns a string in the file format used to save the Event object to disk.
     *
     * @return A string representing the Event object in the file format.
     */
    @Override
    public String toFileFormat() {
        return "E | " + (isDone() ? "1" : "0") + " | " + getDescription() + " | "
                + getPriority() + " | " + from + " | " + to;
    }
}
