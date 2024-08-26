import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime start;
    protected LocalDateTime end;

    public Event(String description, LocalDateTime start, LocalDateTime end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy, hhmma");

        String formattedStart = this.start.format(formatter).replace("AM", "hrs").replace("PM", "hrs");
        String formattedEnd = this.end.format(formatter).replace("AM", "hrs").replace("PM", "hrs");

        return "[E]" + super.toString() + " (from: " + formattedStart + " to: " + formattedEnd + ")";
    }

    @Override
    public String getTaskType() {
        return "Event";
    }

    @Override
    public String getTimeConstraint() {
        return "start: " + this.start + " end: " + this.end;
    }

    public LocalDateTime getStart() {
        return this.start;
    }

    public LocalDateTime getEnd() {
        return this.end;
    }
}
