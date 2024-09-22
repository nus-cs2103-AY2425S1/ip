package botimusprime.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;
    public Event(String description, boolean isDone, LocalDateTime from, LocalDateTime to) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    @Override
    public boolean isEvent() {
        return true;
    }

    @Override
    public boolean isToDo() {
        return false;
    }

    @Override
    public boolean isDeadline() {
        return false;
    }

    public LocalDateTime getFromDate() {
        return from;
    }

    public LocalDateTime getToDate() {
        return to;
    }

    public String saveString() {
        return String.format("E | %s | %s | %s | %s", description, isDone,
                from.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                to.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(),
                from.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                to.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
    }
}