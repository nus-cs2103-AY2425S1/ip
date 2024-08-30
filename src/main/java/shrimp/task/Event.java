package shrimp.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private final LocalDateTime from;
    private final LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to, Boolean hasCompleted) {
        super(description, hasCompleted);
        this.from = from;
        this.to = to;
    }

    public LocalDateTime getFrom() {
        return from;
    }

    public LocalDateTime getTo() {
        return to;
    }

    @Override
    public String toString() {
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return getType() + super.toString() + " (from: " + from.format(pattern) + " to: " + to.format(pattern) + ")";
    }

    @Override
    public Event markAsDone() {
        return new Event(getDescription(), this.from, this.to, true);
    }

    @Override
    public Event markAsNotDone() {
        return new Event(getDescription(), this.from, this.to, false);
    }

    public String getType() {
        return "[E]";
    }
}
