package sumode.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {

    private final String start;
    private final String end;
    private LocalDate startDate;
    private LocalDate endDate;

    public Event(String name, String start, String end) {
        super(name);
        this.start = start;
        this.end = end;

        try {
            startDate = LocalDate.parse(start);
        } catch (DateTimeParseException e) {
            startDate = null;
        }

        try {
            endDate = LocalDate.parse(end);
        } catch (DateTimeParseException e) {
            endDate = null;
        }
    }

    @Override
    public String toString() {
        return "[E]"
                + super.toString()
                + " (from: "
                + (startDate == null ? this.start : this.startDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")))
                + " to: "
                + (endDate == null ? this.end : this.endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")))
                + ")";
    }

    @Override
    public String savedString() {
        return "E | " + super.savedString() + " | " + this.start + " | " + this.end;
    }
}
