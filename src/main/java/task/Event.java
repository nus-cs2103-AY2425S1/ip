package skd.task;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm");

    public Event(String description, String from, String to) {
        super(description, TaskType.EVENT);
        this.from = LocalDateTime.parse(from, DATE_TIME_FORMATTER);
        this.to = LocalDateTime.parse(to, DATE_TIME_FORMATTER);
    }

    public Event(String description, String from, String to, boolean isDone) {
        super(description, TaskType.EVENT, isDone);
        this.from = LocalDateTime.parse(from, DATE_TIME_FORMATTER);
        this.to = LocalDateTime.parse(to, DATE_TIME_FORMATTER);
    }

    public LocalDateTime getFrom() {
        return from;
    }

    public LocalDateTime getTo() {
        return to;
    }

    @Override
    public String getType() {
        return "[E]";
    }

    @Override
    public String toString() {
        return getType() + getStatusIcon() + " " + description + " (from: " + from.format(OUTPUT_FORMATTER) + " to: " + to.format(OUTPUT_FORMATTER) + ")";
    }
}