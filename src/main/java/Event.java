import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime start;
    private LocalDateTime end;
    private static final DateTimeFormatter parseFormatter = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
    private static final DateTimeFormatter printFormatter = DateTimeFormatter.ofPattern("MMM/d/yyyy HH:mm");

    /**
     * Constructor for Event class.
     * @param description a String describing the Event
     * @param start a String describing the start date/time
     * @param end a String describing the end date/time
     */
    public Event(String description, String start, String end) {
        super(description.strip());
        this.start = LocalDateTime.parse(start.strip().substring(5), parseFormatter);
        this.end = LocalDateTime.parse(end.strip().substring(3), parseFormatter);
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    /**
     * Returns a formatted String that represents the Event object and its fields
     * @return a String representation of the Event object
     */
    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)",
                super.toString(), start.format(printFormatter), end.format(printFormatter));
    }
}
