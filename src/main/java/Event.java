import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event (String description, String from, String to) {
        super(description);
        this.from = DateUtils.parseDateTime(from);
        this.to = DateUtils.parseDateTime(to);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " +
                this.from.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a"))
                + " to: "
                + this.to.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a")) + ")";
    }

    @Override
    public String toFileFormat() {
        String task = "E";

        return task + " | " + (this.isDone ? "1" : "0") + " | "
                + this.description + " | " +
                this.from.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a"))
                + " | "
                + this.to.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a")) ;
    }
}
