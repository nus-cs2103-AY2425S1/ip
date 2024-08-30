import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import main.DateTime;

public class Event extends Task {
    private static final String TYPE = "E";
    private LocalDate from;
    private LocalDate to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = DateTime.parseDate(from);
        this.to = DateTime.parseDate(to);
    }

    @Override
    public String toString() {
        return "[" + TYPE + "]" + super.toString() + " (from: " + DateTime.dmy.format(from) + " to: " + DateTime.dmy.format(to) + ")";
    }

    @Override
    public String toStorage() {
        return TYPE + "|" + super.toStorage() + "|" + from + "|" + to;
    }
}
