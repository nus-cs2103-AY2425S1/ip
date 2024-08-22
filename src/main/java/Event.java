import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to, boolean isDone) {
        super(description, TaskType.EVENT, isDone);
        this.from = from; // store the start time
        this.to = to; // store the end time
    }

    @Override
    public String toFileFormat() {
        return "E | " + (getIsDone() ? "1" : "0") + " | " + getDescription() + " | "
                + from.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) + " | "
                + to.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: "
                + from.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a")) + " to: "
                + to.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a")) + ")";
    }
}