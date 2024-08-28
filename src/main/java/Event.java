import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String taskName, LocalDateTime from, LocalDateTime to) {
        super(taskName);
        this.from = from;
        this.to = to;
    }

    public Event(String taskName, boolean isDone, String from, String to) {
        super(taskName);
        this.from = from;
        this.to = to;
        if (isDone) {
            super.markAsDone();
        }
    }

    public String getFrom() {
        return this.from;
    }

    public String getTo() {
        return this.to;
    }

    @Override
    public String toString() {
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");
        return "[E]" + super.toString() + " (from: " + this.from.format(pattern) + " to: " + this.to.format(pattern) + ")";
    }
}
