package cow.tasks;

/**
 * Represents an Event Task.
 */
public class Event extends Task {
    private final String from;
    private final String to;

    /**
     * Creates an Event Task.
     *
     * @param description The Description of the event.
     * @param from from to state the start of the event.
     * @param to to state the end of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Creates an Event Task.
     *
     * @param isDone      The status of the Event.
     * @param description The Description of the Event.
     * @param from        from to state the start of the event.
     * @param to          to state the end of the event.
     */
    public Event(String isDone, String description, String from, String to) {
        super(isDone, description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string representation of the Event Task.
     *
     * @return A string representation of the Event Task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }

    /**
     * Returns the save data of the Event Task.
     *
     * @return The save data of the Event Task.
     */
    @Override
    public String getSaveData() {
        return "E | " + super.getSaveData() + " | " + this.from + " | " + this.to;
    }
}
