import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private final LocalDateTime start;
    private final LocalDateTime end;

    /**
     * Constructor for Event class.
     * @param description a String describing the Event
     * @param start a String describing the start date/time
     * @param end a String describing the end date/time
     */
    public Event(String description, String start, String end) {
        super(description.strip());
        this.start = Formatter.parseDateTimeString(start.strip().substring(5));
        this.end = Formatter.parseDateTimeString(end.strip().substring(3));
    }

    /**
     * Getter for the start field
     * @return the LocalDateTime object representing the start date/time
     */
    public LocalDateTime getStart() {
        return start;
    }

    /**
     * Getter for the end field
     * @return the LocalDateTime object representing the end date/time
     */
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
                super.toString(), Formatter.printDateTime(start), Formatter.printDateTime(end));
    }
}
