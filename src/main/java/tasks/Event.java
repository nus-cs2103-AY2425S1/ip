package tasks;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime start;
    protected LocalDateTime end;

    public Event(String name, LocalDateTime start, LocalDateTime end) {
        super(name);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + " (from: "
                + this.start.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + " to: "
                + this.end.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + ")";
    }

    @Override
    public String saveFormat() {
        return "E" + super.saveFormat() + " | "
                + this.start.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + " | "
                + this.end.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }
}
