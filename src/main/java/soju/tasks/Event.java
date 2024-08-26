package soju.tasks;

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
    public String printToDate() {
        return to.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a"));
    }
    public String printFromDate() {
        return from.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a"));
    }
    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), printFromDate(), printToDate());
    }

    @Override
    public String toFileString() {
        return String.format("E | %d | %s | %s - %s", isDone ? 1 : 0, description, from, to);
    }
}
