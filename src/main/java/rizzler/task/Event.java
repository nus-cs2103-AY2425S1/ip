package rizzler.task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import rizzler.ui.parser.DateTimeParser;

/**
 * Represents an Event with the addition of an <code>eventStart</code> and an <code>eventEnd</code>.
 */
public class Event extends Task {
    private final LocalDate eventStart;
    private final LocalDate eventEnd;

    /**
     * Constructor to create an <code>Event</code> object.
     * @param eventDesc Text description of the event.
     * @param eventStart Date of the event start in <code>YYYY-MM-DD</code> format.
     * @param eventEnd Date of the event end in <code>YYYY-MM-DD</code> format.
     * @throws DateTimeParseException Throws this exception if the user's input does not match the
     *     <code>YYYY-MM-DD</code> format.
     */
    public Event(String eventDesc, String eventStart, String eventEnd) throws DateTimeParseException {
        this(eventDesc, eventStart, eventEnd, false);
    }

    /**
     * Alternative constructor to create an <code>Event</code> object, with an additional parameter to create
     * a completed event.
     * @param eventDesc Text description of the event.
     * @param eventStart Date of the event start in <code>YYYY-MM-DD</code> format.
     * @param eventEnd Date of the event end in <code>YYYY-MM-DD</code> format.
     * @param isDone Boolean indicating whether the task has already been completed.
     * @throws DateTimeParseException Throws this exception if the user's input does not match
     *         the <code>YYYY-MM-DD</code> format.
     */
    public Event(String eventDesc, String eventStart, String eventEnd, boolean isDone) throws DateTimeParseException {
        super(eventDesc, isDone);
        this.eventStart = DateTimeParser.toDatetime(eventStart);
        this.eventEnd = DateTimeParser.toDatetime(eventEnd);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + DateTimeParser.toStr(eventStart)
                + " to: " + DateTimeParser.toStr(eventEnd) + ")";
    }

    @Override
    public String getType() {
        return "Event";
    }

    @Override
    public String toTsv() {
        return getType() + "\t" + super.toTsv() + "\t" + eventStart + "\t" + eventEnd;
    }
}
