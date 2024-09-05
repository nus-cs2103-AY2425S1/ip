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
        assert from != null && !from.isEmpty() : "Start time 'from' cannot be null or empty.";
        assert to != null && !to.isEmpty() : "End time 'to' cannot be null or empty.";
        try {
            this.from = DateTimeParser.parse(from);
            assert this.from != null : "Parsed 'from' date should not be null.";
            this.to = DateTimeParser.parse(to);
            assert this.to != null : "Parsed 'to' date should not be null.";
        } catch (DateTimeParseException e) {
            throw new TarsException("Invalid date format. Please use the format: yyyy-MM-dd HHmm.");
        }
        assert this.from.isBefore(this.to) : "'from' time should be before 'to' time.";
    }

    public void setFrom(String newFrom) throws TarsException {
        assert newFrom != null && !newFrom.isEmpty() : "New 'from' date cannot be null or empty.";
        try {
            this.from = DateTimeParser.parse(newFrom);
            assert this.from.isBefore(this.to) : "'from' time should be before 'to' time after updating.";
        } catch (DateTimeParseException e) {
            throw new TarsException("Invalid date format. Please use the format: yyyy-MM-dd HHmm.");
        }
    }

    public void setTo(String newTo) throws TarsException {
        try {
            this.to = DateTimeParser.parse(newTo);
            assert this.from.isBefore(this.to) : "'from' time should be before 'to' time after updating.";
        } catch (DateTimeParseException e) {
            throw new TarsException("Invalid date format. Please use the format: yyyy-MM-dd HHmm.");
        }
    }

    public String getFrom() {
        assert this.from != null : "'from' date should not be null when retrieving.";
        return DateTimeParser.format(this.from);
    }

    public String getTo() {
        assert this.to != null : "'to' date should not be null when retrieving.";
        return DateTimeParser.format(this.to);
    }

    @Override
    public String toString() {
        assert this.from != null && this.to != null : "'from' and 'to' dates should not be null when generating string representation.";
        return "[E] " + super.toString() + " (from: " + getFrom() + " to: " + getTo() + ")";
    }
}
