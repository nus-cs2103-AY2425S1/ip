// Event class which represents an event task that is a subclass of Task.
public class Event extends Task {
    protected String start;
    protected String end;

    /**
     * Creates a new Event task with description, start time, end time.
     *
     * @param description the task description
     * @param start the start time of the event
     * @param end the end time of the event
     */
    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start + " to: " + end + ")";
    }
}