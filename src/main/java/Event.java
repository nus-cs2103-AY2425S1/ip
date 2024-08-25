/**
 * Represents an event to be recorded. An <code>Event</code> object
 * is represented by three Strings
 * e.g., <code>project meeting, Mon 2pm, to 4pm</code>
 */
public class Event extends Task {

    protected String startTime;
    protected String endTime;


    public Event(String description, String startTime, String endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.startTime
                + " to: " + this.endTime + ")";
    }
}
