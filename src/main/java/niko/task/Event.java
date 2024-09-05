package niko.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

import niko.common.DateTimeParser;

/**
 * Represents an event task with a start and end time.
 */
public class Event extends Task {
    protected LocalDateTime start;
    protected LocalDateTime end;

    /**
     * Constructs an Event task with the specified description, start time, and end time.
     *
     * @param description The description of the event.
     * @param start The start time as a string.
     * @param end The end time as a string.
     */
    public Event(String description, String start, String end) {
        super(description, TaskType.EVENT);
        try {
            this.start = DateTimeParser.convertDate(start);
            this.end = DateTimeParser.convertDate(end);
        } catch (DateTimeParseException ignore) {
            // Intentionally left blank
        }
        try {
            this.start = DateTimeParser.parseDateTime(start);
            this.end = DateTimeParser.parseDateTime(end);
        } catch (DateTimeParseException ignore) {
            // Intentionally left blank
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " "
                + "(from: " + start.format(DateTimeFormatter.ofPattern("MMM d yyyy h:mm a", Locale.ENGLISH))
                + " to: " + end.format(DateTimeFormatter.ofPattern("MMM d yyyy h:mm a", Locale.ENGLISH)) + ")";
    }

    /**
     * Returns the start time of the event.
     *
     * @return The start time as a LocalDateTime object.
     */
    public LocalDateTime getStart() {
        return start;
    }
}
