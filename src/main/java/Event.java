/**
 * Represents an event task with a description, start time, and end time.
 */
public class Event extends Task {

    protected String from;
    protected String to;

    /**
     * Constructs an Event task with description, start time, and end time.
     *
     * @param description The description of the event.
     * @param from The start time of the event.
     * @param to The end time of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string representation of this event task.
     *
     * @return A string representation of this event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}