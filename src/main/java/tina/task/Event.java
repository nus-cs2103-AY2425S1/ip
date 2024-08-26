package tina.task;

import tina.Parser;
import java.time.LocalDateTime;

/**
 * Represents an event task with a start and end time.
 * An <code>Event</code> object contains a string description, a boolean to mark completion,
 * and the start and end date/time of the event.
 */
public class Event extends Task {
    private final LocalDateTime start;
    private final LocalDateTime end;

    /**
     * Constructs a new <code>Event</code> object with the specified description, start time, and end time when parsed from user.
     *
     * @param des The description of the event.
     * @param start The start time of the event, in a parsable date-time string format.
     * @param end The end time of the event, in a parsable date-time string format.
     */
    public Event(String des, String start, String end) {
        super(des);
        this.end = Parser.parseDate(end);
        this.start = Parser.parseDate(start);
    }

    /**
     * Constructs a new <code>Event</code> object with the specified description,
     * completion status, start time, and end time when parsed from storage
     *
     * @param des The description of the event.
     * @param isMark The completion status of the event.
     * @param start The start time of the event, in a parsable date-time string format.
     * @param end The end time of the event, in a parsable date-time string format.
     */
    public Event(String des, boolean isMark, String start, String end) {
        super(des);
        this.end = LocalDateTime.parse(end);
        this.start = LocalDateTime.parse(start);
        this.isMark = isMark;
    }

    /**
     * Returns the description of the event, including its type and formatted start and end times.
     *
     * @return A string of the event description.
     */
    @Override
    public String getDes() {
        return "[E]" + super.getDes() + " (from: " + Parser.formatDate(start) + " to: " + Parser.formatDate(end) + ")";
    }

    /**
     * Returns a string representation of the event for storage purposes.
     *
     * @return A string of the event in a format for saving to a file.
     */
    @Override
    public String toString() {
        return String.format("E %d %s | %s | %s", isMark? 1 : 0, des, start, end);
    }
}
