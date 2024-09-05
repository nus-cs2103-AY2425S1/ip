import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(DateTimeFormatter.ofPattern("MMM d yyyy h:mma")) + " to: " + to.format(DateTimeFormatter.ofPattern("MMM d yyyy h:mma")) + ")";
    }

    @Override
    public String toFileString() {
        return String.format("E | %d | %s | %s | %s", (isDone ? 1 : 0), description, from.format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm")), to.format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm")));
    }
}
