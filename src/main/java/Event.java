import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// class for Tasks which are events
public class Event extends Task{
    protected LocalDateTime from;
    protected LocalDateTime to;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    public Event(String description, String from, String to) {
        super(description);
        this.from = LocalDateTime.parse(from, formatter);
        this.to = LocalDateTime.parse(to, formatter);
    }
    public String getFrom() {
        return from.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a"));
    }
    public String getTo() {
        return to.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a"));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + getFrom() +
                " to:" + getTo() + ")";
    }
}
