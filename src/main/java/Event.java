import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    protected LocalDateTime from;
    protected LocalDateTime to;
    static final protected DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd HHmm");

    public Event(String description, String from, String to) {
        super(description);
        this.from = LocalDateTime.parse(from, formatter);
        this.to = LocalDateTime.parse(to, formatter);
    }

    public Event(String description, String from, String to, int done) {
        super(description);
        this.from = LocalDateTime.parse(from, formatter);
        this.to = LocalDateTime.parse(to, formatter);
        if (done == 1) {
            this.isDone = true;
        }
    }

    @Override
    public String getTaskType() {
        return "E";
    }

    @Override
    public String toSaveFormat() {
        return String.format("%s | %d | %s | %s | %s", this.getTaskType(), (this.isDone ? 1 : 0), this.description, this.from.format(formatter), this.to.format(formatter));
    }

    @Override
    public String toString() {
        return super.toString() + " (from: " + this.from.format(DateTimeFormatter.ofPattern("MMM d uuuu, h:mma")) + " to: " + this.to.format(DateTimeFormatter.ofPattern("MMM d uuuu, h:mma")) + ")";
    }
}
