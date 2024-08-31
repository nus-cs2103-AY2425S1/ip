import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;
    private static final DateTimeFormatter OUTPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy, hh:mm a");

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
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
        return "[E]" + super.toString() + " (from: " + from.format(OUTPUT_DATE_FORMAT) + " to: " + to.format(OUTPUT_DATE_FORMAT) + ")";
    }
}
