package rob;

/**
 * Represents a task is an event and lasts for a specified period of time.
 */
public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Constructs a new event task with the specified description and time from and to.
     *
     * @param description The description of the deadline task.
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

    @Override
    public String toSaveString() {
        return "[E] | " + getStatusIcon() + " | " + description + " | " + from + " | " + to;
    }
}
