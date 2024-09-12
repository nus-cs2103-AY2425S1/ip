package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task.
 */
public class Event extends Task {
    /**
     * The date-time when the event starts.
     */
    private LocalDateTime start;

    /**
     * The date-time when the event ends.
     */
    private LocalDateTime end;

    /**
     * Constructor for a new event task.
     * @param name the name of the event task
     * @param start the start of the event
     * @param end the end of the event
     */
    public Event(String name, LocalDateTime start, LocalDateTime end) {
        super(name);

        assert start != null;
        assert end != null;

        this.start = start;
        this.end = end;
    }

    /**
     * Get the string representation of the event task.
     * @return the string representation of the event task
     */
    @Override
    public String toString() {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy");
        String startString = this.start.format(f);
        String endString = this.end.format(f);
        return String.format("[E]%s (from: %s to: %s)", super.toString(), startString, endString);
    }
}
