/**
 * Event class includes the name of the event, start date/time of the event and the date/time when the event ends.
 * Subclass of Task class.
 */
public class Event extends Task {
    protected String start;
    protected String end;

    /**
     * Constructor for event instance.
     *
     * @param description Name of event task.
     * @param start Start date/time of event.
     * @param end End date/time of event.
     */
    public Event(String description, String start, String end) {
        super(description, TaskType.Event);
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

    /**
     * Formats Event for saving.
     *
     * @return String Formatted details of Event.
     */
    @Override
    public String saveDetails() {
        return "E | " + super.saveDetails() + " | " + start + " | " + end;
    }
}
