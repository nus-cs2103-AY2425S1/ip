package pacman;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an event. A <code>Event</code> object corresponds to
 * an event's name, a start date of the event, and a end date of the event
 */
public class Event extends Task {
    private final LocalDate from;
    private final LocalDate to;

    Event(String task, String from, String to) throws DateTimeParseException {
        super(task);
        this.from = LocalDate.parse(from);
        this.to = LocalDate.parse(to);
    }

    /**
     * Returns a <code>String</code> that is readable and writeable by <code>Storage</code>
     *
     * @return a <code>String</code> that is readable and writeable by <code>Storage</code>
     */
    public String toFile() {
        return "E/" + super.toFile() + "/" + from + "/" + to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + from.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
                + " to: " + to.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
