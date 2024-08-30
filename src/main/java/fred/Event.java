package fred;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;
    DateTimeFormatter formatter;

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
        formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), from.format(formatter), to.format(formatter));
    }

    @Override
    public String getDataFormat() {
        return "E" + super.getDataFormat() + " | " + from.format(formatter) + "-" + to.format(formatter);
    }
}

