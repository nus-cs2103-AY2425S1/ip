package Pebble;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate from;
    protected LocalDate to;
    protected String stringFrom;
    protected String stringTo;

    public Event(String description, String from, String to) {
        super(description);
        try {
            this.from = LocalDate.parse(from);
        } catch (DateTimeException e) {
            this.stringFrom = from;
        }
        try {
            this.to = LocalDate.parse(to);
        } catch (DateTimeException e) {
            this.stringTo = to;
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: "
                + (from != null ? from.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) : stringFrom)
                + " to: "
                + (to != null ? to.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) : stringTo)
                + ")";
    }
}
