package rizzler.task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import rizzler.ui.parser.DateTimeParser;

/**
 * Represents an Event with the addition of an <code>eventStart</code> and an <code>eventEnd</code>.
 */
public class Event extends Task {
    private final String eventStart;
    private final String eventEnd;

    /**
     * Constructs an <code>Event</code> object.
     * @param eventDesc Text description of the event.
     * @param eventStart <code>String</code> describing the event start time/date.
     * @param eventEnd <code>String</code> describing the event end time/date.
     */
    public Event(String eventDesc, String eventStart, String eventEnd) {
        this(eventDesc, eventStart, eventEnd, false);
    }

    /**
     * Constructs an <code>Event</code> object.
     * @param eventDesc Text description of the event.
     * @param eventStart <code>String</code> describing the event start time/date.
     * @param eventEnd <code>String</code> describing the event end time/date.
     * @param isDone Boolean to indicate if the task being created has been marked as done.
     */
    public Event(String eventDesc, String eventStart, String eventEnd, boolean isDone) throws DateTimeParseException {
        super(eventDesc, isDone);
        this.eventStart = eventTimeToString(eventStart);
        this.eventEnd = eventTimeToString(eventEnd);
    }

    private String eventTimeToString(String eventTime) {
        if (DateTimeParser.canParse(eventTime)) {
            LocalDate eventDatetime = LocalDate.parse(eventTime);
            return DateTimeParser.toStr(eventDatetime);
        } else {
            return eventTime;
        }
    }

    /**
     * Generates a string ready for printing as a description of an <code>Event</code>.
     * This includes the <code>Event</code> description, event start, and event end.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + eventStart + " to: " + eventEnd + ")";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getType() {
        return "Event";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toTsv() {
        return getType() + "\t" + super.toTsv() + "\t" + eventStart + "\t" + eventEnd;
    }
}
