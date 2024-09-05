package bocchi.task;

import bocchi.exception.BocchiException;
import bocchi.util.BocchiDateTimeFormatter;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * Represents an event task.
 */
public class Event extends Task {
    /**
     * The start date/time of this tasks.Event.
     */
    protected LocalDateTime fromDateTime;
    /**
     * The end date/time of this event.
     */
    protected LocalDateTime toDateTime;

    /**
     * The constructor.
     *
     * @param description  tasks.Event description.
     * @param fromDateTime Start date/time of this event.
     * @param toDateTime   End date/time of this event.
     * @throws BocchiException        If any parameter is empty.
     * @throws DateTimeParseException If the date/time format is invalid.
     */
    public Event(String description, String fromDateTime, String toDateTime)
            throws BocchiException, DateTimeParseException {
        super(description);
        if (fromDateTime == null || toDateTime == null) {
            throw new BocchiException("So..sorry, but you have specify both start and end time for an event.");
        }
        this.fromDateTime = BocchiDateTimeFormatter.parse(fromDateTime);
        this.toDateTime = BocchiDateTimeFormatter.parse(toDateTime);
    }


    public LocalDateTime getToDateTime() {
        return toDateTime;
    }


    public LocalDateTime getFromDateTime() {
        return fromDateTime;
    }

    /**
     * Returns a string representation of this event.
     *
     * @return A string representation of this event.
     */
    @Override
    public String toString() {
        return "[EVENT]" + super.toString() + " (from: " + BocchiDateTimeFormatter.toString(fromDateTime)
                + "; to: " + BocchiDateTimeFormatter.toString(toDateTime) + ")";
    }
}
