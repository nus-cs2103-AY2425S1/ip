package duck.tasks;

/**
 * Class representing an "event" task.
 */
public class Event extends Task {
    private final DateAndTime from;
    private final DateAndTime to;

    /**
     * Constructor for Event.
     *
     * @param description Task description.
     * @param from        Start date.
     * @param to          End date.
     */
    public Event(String description, DateAndTime from, DateAndTime to) {
        super(description);

        this.from = from;
        this.to = to;
    }

    @Override
    public String getSaveString() {
        return String.format("E,%s,%s,%s,%s", isDone, description, from.getOriginalString(), to.getOriginalString());
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + String.format(" (from: %s to: %s)", from.toString(), to.toString());
    }
}
