import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private Parser parser = new Parser();
    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public Event(String description, LocalDateTime from, LocalDateTime to, Boolean isDone) {
        super(description, isDone);
        this.from = from;
        this.to = to;

    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " +
                parser.dateToString(from) + ", to: " +
                        parser.dateToString(to) + ")";
    }

    public String toSave() {
        return "E" + " | " + super.getDoneStatus()
                + " | " + super.getDescription()
                + " | " + parser.dateToString(from)
                + " to " + parser.dateToString(to);
    }
}
