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

    @Override
    public String dbReadableFormat() {
        return String.format("Event|%d|%s|%s|%s", this.isDone ? 1 : 0, this.description, this.from, this.to);
    }

    private String fromToString() {
        return from.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a"));
    }

    private String toToString() {
        return to.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a"));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + fromToString() + " to: " + toToString() + ")";
    }
}
