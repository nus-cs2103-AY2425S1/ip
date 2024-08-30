package muffin;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDate from;
    protected LocalDate to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = LocalDate.parse(from);
        this.to = LocalDate.parse(to);
    }

    @Override
    public String toString() {
        String f = from.format(DateTimeFormatter.ofPattern("E, MMM d yyyy"));
        String t = to.format(DateTimeFormatter.ofPattern("E, MMM d yyyy"));
        return String.format("[E]%s (from: %s to: %s)", super.toString(), f, t);
    }
}
