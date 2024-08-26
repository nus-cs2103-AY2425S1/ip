import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class Event extends Task {
    private LocalDateTime start;
    private LocalDateTime end;

    public static String format = "event <description> /from <start date> /to <end date>";

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.start = from;
        this.end = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.start.format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm"))
                + " to " + this.end.format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm")) + ")";
    }
}
