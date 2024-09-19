package kita;

import java.time.LocalDate;

/**
 * A "Event" tasks with a "from" and "to"
 */
public class Event extends Task {
    private final LocalDate from;
    private final LocalDate to;

    /**
     * Initialises an "Event" task
     *
     * @param name The name of the task
     * @param from The time this event is starting in the form of yyyy-mm-dd
     * @param to The time this event is ending in the form of yyyy-mm-dd
     */
    public Event(String name, String from, String to) {
        super(name);
        this.from = DateFormatters.getDateFromStr(from);
        this.to = DateFormatters.getDateFromStr(to);
    }

    public LocalDate getFrom() {
        return this.from;
    }

    public LocalDate getTo() {
        return this.to;
    }

    @Override
    public String type() {
        return "E";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + DateFormatters.getStrFromDate(this.from) + " to: "
                + DateFormatters.getStrFromDate(this.to) + ")";
    }
}
