package tasks;

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
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")) + " to: " + this.to.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")) + ")";
    }

    @Override
    public String saveFormat() {
        return "E | " + super.saveFormat() + " | " + this.from.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + " | " + this.to.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }
}
