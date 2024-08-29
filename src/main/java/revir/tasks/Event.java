package revir.tasks;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event that extends the Task class.
 * An event has a description, start time, and end time.
 * 
 * Usage: event <description> /from <start date> /to <end date>
 */
public class Event extends Task {
    private LocalDateTime start;
    private LocalDateTime end;

    public static String format = "event <description> /from <start date> /to <end date>";

    /**
     * Constructs a new Event object with the given description, start time, and end time.
     *
     * @param description the description of the event
     * @param from the start time of the event
     * @param to the end time of the event
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.start = from;
        this.end = to;
    }

    /**
     * Returns a string representation of the Event object.
     *
     * @return The string representation of the Event object.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.start.format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm"))
                + " to " + this.end.format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm")) + ")";
    }
}
