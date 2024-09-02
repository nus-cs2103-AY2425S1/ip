package task;

import java.time.LocalDateTime;

public class Event extends Task {
    private LocalDateTime start;
    private LocalDateTime end;

    public Event(String description, LocalDateTime start, LocalDateTime end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    public Event(String description, String start, String end) {
        this(description, Converter.InputToDateTime(start), Converter.InputToDateTime(end));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + Converter.DateTimeToOutput(start) + " to: " + Converter.DateTimeToOutput(end) + ")";
    }
}