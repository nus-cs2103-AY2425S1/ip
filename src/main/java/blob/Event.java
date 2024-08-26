package blob;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime start;
    private LocalDateTime end;

    public Event(String name, boolean isDone, String start, String end) {
        super(name, isDone);
        this.start = LocalDateTime.parse(start);
        this.end = LocalDateTime.parse(end);
        super.type = "E";
    }

    @Override
    public String toString() {
        return "[E]" + "[" + this.check() + "] " + this.name + " (from: " + start.format(DateTimeFormatter.ofPattern("MMM d HH:mm")) + " to: " + end.format(DateTimeFormatter.ofPattern("MMM d HH:mm")) + ")";
    }

    public LocalDateTime getStart() {
        return this.start;
    }

    public LocalDateTime getEnd() {
        return this.end;
    }
}
