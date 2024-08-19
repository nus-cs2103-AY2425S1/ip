public class Event extends Task {
    /**
     * The date-time when the event starts.
     */
    private String start;

    /**
     * The date-time when the event ends.
     */
    private String end;

    /**
     * Constructor for a new event task.
     * @param name the name of the event task
     * @param start the start of the event
     * @param end the end of the event
     */
    public Event(String name, String start, String end) {
        super(name);
        this.start = start;
        this.end = end;
    }

    /**
     * Get the string representation of the event task.
     * @return the string representation of the event task
     */
    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), this.start, this.end);
    }
}
