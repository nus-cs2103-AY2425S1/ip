package oliver;

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

    @Override
    public String getFormatted() {
        return "E|" + super.getStatusIcon() + "|" + super.description + "|" + this.from + "|" + this.to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " +
                from.format(DateTimeFormatter.ofPattern("MMM d yyyy, h.mm a")) + " to: " +
                to.format(DateTimeFormatter.ofPattern("MMM d yyyy, h.mm a")) + ")";
    }
}
