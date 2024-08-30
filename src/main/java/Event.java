import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDateTime start;
    protected LocalDateTime end;

    public Event(String description, String start, String end) throws LightException {
        super(description);
        this.start = Parser.dateTimeParser(start, DateTimeFormatter.ofPattern("[d/MM/yyyy HHmm][MMM d yyyy HHmm]"));
        this.end = Parser.dateTimeParser(end, DateTimeFormatter.ofPattern("[d/MM/yyyy HHmm][MMM d yyyy HHmm]"));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + start.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm")) + " to: " + end.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm")) + ")";
    }
}