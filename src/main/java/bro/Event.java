package bro;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toLoad() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return "[E]" + super.toString() + " /from " +
                from.format(format) + " /to " + to.format(format);
    }

    @Override
    public String toString() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd LLL yyyy HHmm");
        return "[E]" + super.toString() + " (from: " +
                from.format(format) + " to: " + to.format(format) + ")";
    }
}

