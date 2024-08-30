package pebble;

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
            this.from = LocalDate.parse(from.trim());
        } catch (DateTimeException e) {
            this.stringFrom = from;
        } catch (NullPointerException e) {
            this.stringFrom = "null";
        }
        try {
            this.to = LocalDate.parse(to.trim());
        } catch (DateTimeException e) {
            this.stringTo = to;
        } catch (NullPointerException e) {
            this.stringTo = "null";
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
