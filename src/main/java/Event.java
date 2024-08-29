import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = LocalDateTime.parse(from, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        this.to = LocalDateTime.parse(to, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    @Override
    public String toFileString() {
        return String.format("E | %d | %s | %s | %s", isDone ? 1 : 0, description,
                from.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")),
                to.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mma"))
                + " to: " + to.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mma")) + ")";
    }
}
