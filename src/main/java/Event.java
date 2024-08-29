import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private Parser parser;
    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        parser = new Parser();
        this.from = from;
        this.to = to;
    }

    public Event(String description, LocalDateTime from, LocalDateTime to, Boolean isDone) {
        super(description, isDone);
        parser = new Parser();
        this.from = from;
        this.to = to;

    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " +
                from.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ", to: " +
                        to.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    public String toSave() {
        return "E" + " | " + super.getDoneStatus()
                + " | " + super.getDescription()
                + " | " + parser.dateToString(from)
                + " to " + parser.dateToString(to);
    }
}
