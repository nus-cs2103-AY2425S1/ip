/**
 * This class encapsulates an Event type task.
 * An Event starts at a specific time and ends at a specific time.
 */
public class Event extends Task {
    /** The time at which the event starts */
    private String from;
    /** The time at which the event ends */
    private String to;

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
