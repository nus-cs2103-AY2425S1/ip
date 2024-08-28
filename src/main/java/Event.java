import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.to = to;
        this.from = from;
    }

    @Override
    public String toString() {
        return "[E][" + this.getStatusIcon() + "] " + this.getDescription() + "(from: " + this.getFrom()
                + " to: " + this.getTo() + ")";
    }

    public String getFrom() {
        return from.format(DateTimeFormatter.ofPattern("MMM dd, yyyy HH:mm"));
    }

    public String getTo() {
        return to.format(DateTimeFormatter.ofPattern("MMM dd, yyyy HH:mm"));
    }
}
