import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;
    private final DateTimeFormatter display_format = DateTimeFormatter.ofPattern("MMM-dd-yyyy HHmm");
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " " +
                "(from: " + from.format(display_format) + " to: " + to.format(display_format) + ")";
    }

    @Override
    public String toFileString() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " +
                from.format(display_format) + " | " + to.format(display_format);
    }
}
