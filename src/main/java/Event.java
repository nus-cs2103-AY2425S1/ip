/**
 * The Event class represents a task that occurs within a specific time range.
 */
public class Event extends Task {

    protected String startDate;
    protected String endDate;

    /**
     * Constructs a new Event task with the specified description, start date, and end date.
     *
     * @param description The description of the event.
     * @param start The start date and time of the event.
     * @param end The end date and time of the event.
     */
    public Event(String description, String start, String end) {
        super(description);
        this.startDate = start;
        this.endDate = end;
    }

    /**
     * Returns the string representation of the Event task
     *
     * @return A string representing the Event task
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.startDate + " to: " + this.endDate + ")";
    }
}
