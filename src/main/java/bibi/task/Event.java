package bibi.task;

/**
 * Represents the Event task that contains a description, and the interval
 * in which the event will occur in.
 */
public class Event extends Task {
    private String from;
    private String to;

    /**
     * Constructs a new Event description that has the specified description and the
     * interval in which the event will occur.
     *
     * @param description The description of the event.
     * @param from The start of the event.
     * @param to The end of the event.
     */
    public Event(String description, String from, String to) {
        // Call bibi.description.Task constructor
        super(description);

        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return String.format("[E] %s (%s - %s)", super.toString(), this.from, this.to);
    }
}
