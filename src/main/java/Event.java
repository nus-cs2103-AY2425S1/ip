import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;

    // Using a date-time format
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    public Event(String description, String from, String to) {
        super(description);
        this.from = LocalDateTime.parse(from, DATE_TIME_FORMATTER);
        this.to = LocalDateTime.parse(to, DATE_TIME_FORMATTER);
    }

    @Override
    public String toSaveFormat() {
        return "E | " + (isDone() ? "1" : "0") + " | " + getDescription() + " | " + from.format(DATE_TIME_FORMATTER) + " | " + to.format(DATE_TIME_FORMATTER);
    }

    @Override
    public String toString() {
        return super.toString() + " (from: " + from.format(DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm a")) +
                " to: " + to.format(DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm a")) + ")";
    }
}
