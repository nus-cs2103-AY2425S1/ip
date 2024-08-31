package evan.task;

/**
 * Represents an Event task that the chatbot stores.
 */
public class Event extends Task {
    protected final DateTime from;
    protected final DateTime to;

    /**
     * Instantiates an Event object with the given description, start date/time, and end date/time.
     *
     * @param description Description of the Event.
     * @param from When the Event starts.
     * @param to When the Event ends.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = DateTime.parseInput(from);
        this.to = DateTime.parseInput(to);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String encodeAsString() {
        return String.format("E | %s | %s | %s | %s", (this.isDone ? "1" : "0"), description, from, to);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), from, to);
    }
}