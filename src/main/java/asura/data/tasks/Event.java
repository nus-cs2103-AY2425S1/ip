package asura.data.tasks;

/**
 * Represents an Event.
 */
public class Event extends Task {
    protected String start;
    protected String end;

    /**
     * Creates an Event with the specified description, start and end date.
     * @param description The description of the event.
     * @param start The start date of the event.
     * @param end The end date of the event.
     */
    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start + " to: " + end + ")";
    }
}
