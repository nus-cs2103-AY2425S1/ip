import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    private LocalDateTime start;
    private LocalDateTime end;

    public Event(String description, String start, String end) throws DateTimeParseException {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        this.start = LocalDateTime.parse(start, formatter);
        this.end = LocalDateTime.parse(end, formatter);
    }

    public String getTaskType() {
        return "[E]";
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm a");
        String startFormatted = this.start.format(formatter);
        String endFormatted = this.end.format(formatter);
        return String.format("%s%s (from: %s to: %s)", this.getTaskType(),
                super.toString(), startFormatted, endFormatted);
    }
}
