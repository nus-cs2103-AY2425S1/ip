package gale;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;
    private DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM d yyyy HH:mm");

    public Event(String description, String from, String to) {
        super(description);
        this.from = Parser.parseDateTime(from);
        this.to = Parser.parseDateTime(to);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from.format(outputFormatter)
                + " to: " + this.to.format(outputFormatter) + ")";
    }

    @Override
    public String toFileString() {
        return String.format("E | %d | %s | %s | %s", super.status() ? 1 : 0, getDescription(),
                from.format(Parser.getFormatters().get(0)),
                to.format(Parser.getFormatters().get(0)));
    }
}