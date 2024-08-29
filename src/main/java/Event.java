/**
 * Represents a type of Task that has a start and end time.
 */
public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Constructs an Event task using the given description, from and to date.
     * @param description The String description of the Event.
     * @param from The starting time of the Event.
     * @param to The ending time of the Event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(" + from + to + ")";
    }
}
