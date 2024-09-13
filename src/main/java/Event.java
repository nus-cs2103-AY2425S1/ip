
/**
 * Represents an event task.
 * An Event object corresponds to a task represented by a description, a start time, and an end time.
 */
public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Constructor for Event class.
     *
     * @param description The description of the task.
     * @param from The start time of the event.
     * @param to The end time of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the start time of the event.
     *
     * @return Start time of the event.
     */
    public String getFrom() {
        return this.from;
    }

    /**
     * Returns the end time of the event.
     *
     * @return End time of the event.
     */
    public String getTo() {
        return this.to;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}