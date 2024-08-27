import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy, hh:mm a");

    public Event(String description, String from, String to) {
        super(description, TaskType.EVENT);
        this.from = LocalDateTime.parse(from, INPUT_FORMAT);
        this.to = LocalDateTime.parse(to, INPUT_FORMAT);
    }

    public LocalDateTime getFrom() {
        return from;
    }

    public LocalDateTime getTo() {
        return to;
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s (from: %s to: %s)", type.getSymbol(), status.getSymbol(), description, 
                             from.format(OUTPUT_FORMAT), to.format(OUTPUT_FORMAT));
    }
}