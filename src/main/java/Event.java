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

    public LocalDateTime getFrom() {
        return LocalDateTime.from(this.from);
    }

    public LocalDateTime getTo() {
        return LocalDateTime.from(this.to);
    }

    public String dateToString(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("d MMM yyyy HH:mm"));
    }

    @Override
    public String toFileString() {
        return "E" + super.toFileString() + " | " + this.from.toString() + " | " + this.to.toString();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + dateToString(this.from) + " to: " + dateToString(this.to) + ")";
    }
}
