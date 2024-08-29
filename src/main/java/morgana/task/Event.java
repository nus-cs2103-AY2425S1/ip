package morgana.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private final LocalDateTime start;
    private final LocalDateTime end;

    public Event(String description, LocalDateTime start, LocalDateTime end) {
        super(TaskType.EVENT, description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toFileFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return "%s | %s | %s".formatted(super.toFileFormat(), start.format(formatter), end.format(formatter));
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
        return "%s (from: %s to: %s)".formatted(super.toString(), start.format(formatter), end.format(formatter));
    }
}
