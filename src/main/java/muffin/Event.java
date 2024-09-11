package muffin;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event with a description and a specified date range.
 * The event starts on the "from" date and ends on the "to" date.
 */
public class Event extends Task {

    /**
     * The start date of the event.
     */
    protected LocalDate from;

    /**
     * The end date of the event.
     */
    protected LocalDate to;

    /**
     * Constructs an Event with the specified description, start date, and end date.
     *
     * @param description A brief description of the event.
     * @param from The start date of the event in the format "yyyy-MM-dd".
     * @param to The end date of the event in the format "yyyy-MM-dd".
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = LocalDate.parse(from);
        this.to = LocalDate.parse(to);
    }

    @Override
    public String toFormattedString() {
        return String.format("E|%s|%s|%s|%s", checkStatus(this), this.description, this.from, this.to);
    }

    @Override
    public String toString() {
        String f = from.format(DateTimeFormatter.ofPattern("E, MMM d yyyy"));
        String t = to.format(DateTimeFormatter.ofPattern("E, MMM d yyyy"));
        return String.format("[E]%s (from: %s to: %s)", super.toString(), f, t);
    }
}
