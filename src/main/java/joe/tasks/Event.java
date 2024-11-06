package joe.tasks;

import joe.utils.Parser;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * Represents an Event task.
 */
public class Event extends Task {
    private final LocalDateTime start;
    private final LocalDateTime end;

    /**
     * Constructor for Event class.
     *
     * @param description a String describing the Event
     * @param start       a String describing the start date/time
     * @param end         a String describing the end date/time
     */
    public Event(String description, LocalDateTime start, LocalDateTime end) {
        super(description.strip());
        assert (!description.isEmpty());
        this.start = start;
        this.end = end;
    }

    /**
     * Returns the number of days until the event.
     *
     * @param dateTime the date/time provided by the user
     * @return the number of days until the event
     */
    public long daysTillEvent(LocalDateTime dateTime) {
        return ChronoUnit.DAYS.between(dateTime, start);
    }

    /**
     * Returns a formatted String representing the Event object and its fields for saving to file
     *
     * @return a String saved representation of the Event object
     */
    @Override
    public String saveRepr() {
        return String.format("E | %s | from %s | to %s",
                super.saveRepr(),
                Parser.formatDateTime(start),
                Parser.formatDateTime(end));
    }

    /**
     * Returns a formatted String that represents the Event object and its fields
     *
     * @return a String representation of the Event object
     */
    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)",
                super.toString(), Parser.printDateTime(start), Parser.printDateTime(end));
    }

    @Override
    public LocalDateTime getTime() {
        return start;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Event other) {
            return super.equals(obj)
                    && this.start.equals(other.start)
                    && this.end.equals(other.end);
        }
        return false;
    }
}
