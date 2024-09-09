package bobby.ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task that occurs within a specific time range.
 * An Event task has a description, a start time, and an end time.
 */
public class Events extends Task {

    /**
     * The start time of the Event
     */
    protected LocalDateTime start;

    /**
     * The end time of the Event
     */
    protected LocalDateTime end;

    /**
     * Constructs an Event with the specified description, start time, and end time.
     *
     * @param description The description of the event.
     * @param start The start time of the event.
     * @param end The end time of the event.
     */
    public Events(String description, LocalDateTime start, LocalDateTime end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
        return "[E]" + super.toString() + " (from: " + start.format(formatter)
                + " to: " + end.format(formatter) + ")";
    }

    @Override
    public String toStore() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
        return "E/" + super.getStatus() + "/" + description + "/"
                + start.format(formatter) + "/" + end.format(formatter);
    }
    @Override
    public LocalDateTime getBy() {
        return end;
    }
    @Override
    public LocalDateTime getFrom() {
        return start;
    }
}
