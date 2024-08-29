package yoda.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime start;
    protected LocalDateTime end;

    public Event(String description, LocalDateTime start, LocalDateTime end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String getData() {
        String isDone = this.isDone ? "1" : "0";
        return "E | " + isDone + " | " + this.description + " | " + this.start + " | " + this.end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start.format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm"))
                + " to: " + end.format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm")) + ")";
    }
}
