package ginger.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Encapsulates an event which contains a title of the event, completion status of the event and the start and
 * end times of the event.
 */
public class Event extends Task {
    private final LocalDateTime start;
    private final LocalDateTime end;
    public Event(String title, LocalDateTime start, LocalDateTime end) {
        super(title);
        this.start = start;
        this.end = end;
    }

    public Event(String title, LocalDateTime start, LocalDateTime end, boolean isCompleted) {
        super(title, isCompleted);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toDbString() {
        return String.format("E %s %s | %s", super.toDbString(), this.start, this.end);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm");
        return String.format("[E]%s (%s - %s)", super.toString(),
                this.start.format(formatter), this.end.format(formatter));
    }
}
