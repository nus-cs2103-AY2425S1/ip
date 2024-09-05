package ipman;

/**
 * Represents an event task with a start and end time.
 * Extends the Task class to include the event duration.
 * Provides methods to format and display the event details.
 *
 * @author miloaisdino
 */
public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Constructs an Event task with a specified description, start time, and end time.
     *
     * @param description The name or description of the event.
     * @param from The start time of the event.
     * @param to The end time of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
