package guy.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
 * Representation of an event.
 * Has a start and end time.
 */
public class Event extends Task {
    protected LocalDateTime start;
    protected LocalDateTime end;

    /**
     * Constructs a new Event task.
     *
     * @param name description of the event
     * @param start the event's start time
     * @param end the event's end time
     */
    public Event(String name, LocalDateTime start, LocalDateTime end) {
        super(name);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + " (from: "
                + this.start.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + " to: "
                + this.end.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + ")";
    }

    @Override
    public String saveFormat() {
        return "E" + super.saveFormat() + " | "
                + this.start.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + " | "
                + this.end.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }
}
