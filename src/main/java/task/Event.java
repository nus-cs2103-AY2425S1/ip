package task;

import java.time.LocalDateTime;

/**
 * Represent a task as event with starting time and ending time
 */
public class Event extends Task {
    private LocalDateTime start;
    private LocalDateTime end;

    /**
     * Constructor of the Event
     * @param description Description of the event
     * @param start The starting time of the event
     * @param end The ending time of the event
     */
    public Event(String description, LocalDateTime start, LocalDateTime end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Constructor of the Event
     * @param description Description of the event
     * @param start The starting time of the event (In string format, to be converted later)
     * @param end The ending time of the event (In string format, to be converted later)
     */
    public Event(String description, String start, String end) {
        this(description, Converter.inputToDateTime(start), Converter.inputToDateTime(end));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
            + " (from: " + Converter.dateTimeToOutput(start)
            + " to: " + Converter.dateTimeToOutput(end) + ") "
            + getTagsToString();
    }
}
