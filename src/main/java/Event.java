import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime start;
    private LocalDateTime end;
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm a");

    public Event(String desc, String start, String end) throws InvalidTaskException {
        super(desc, "E");
        try {
            this.start = LocalDateTime.parse(start, INPUT_FORMAT);
            this.end = LocalDateTime.parse(end, INPUT_FORMAT);
            if (this.end.isBefore(this.start)) {
                throw new InvalidTaskException("End time cannot be before start time");
            }
        } catch (Exception e) {
            throw new InvalidTaskException("Invalid date format. Use yyyy-MM-dd HHmm");
        }
    }

    public LocalDateTime getStartDateTime() {
        return start;
    }

    public LocalDateTime getEndDateTime() {
        return end;
    }

    public String getStart() {
        return start.format(INPUT_FORMAT);
    }

    public String getEnd() {
        return end.format(INPUT_FORMAT);
    }

    @Override
    public String toString() {
        return super.toString() + " (from: " + start.format(OUTPUT_FORMAT) + " to: " + end.format(OUTPUT_FORMAT) + ")";
    }

    @Override
    public boolean isOnDate(LocalDate date) {
        return (getStartDateTime().toLocalDate().equals(date) || getEndDateTime().toLocalDate().equals(date));
    }
}