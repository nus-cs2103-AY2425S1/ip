package pacman;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

/**
 * Represents an event. A <code>Event</code> object corresponds to
 * an event's name, a start date of the event, and a end date of the event
 */
public class Event extends Task {
    private final LocalDate from;
    private final LocalDate to;

    Event(String task, String from, String to) {
        super(task);
        this.from = LocalDate.parse(from);
        this.to = LocalDate.parse(to);
    }

    /**
     * Return a <code>String</code> that is readable and writeable by <code>Storage</code>
     *
     * @return a <code>String</code> that is readable and writeable by <code>Storage</code>
     */
    public String toFile() { return "E/" + super.toFile() + "/" + from + "/" + to; }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + from.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + " to: " + to.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
