package michael;

public class Event extends Task {
    private String start;
    private String end;

    /**
     * Initialises a new Event object.
     *
     * @param taskName Name of event.
     * @param start Time at which event starts.
     * @param end Time at which event ends.
     */
    public Event(String taskName, String start, String end) {
        super(taskName);
        this.start = start;
        this.end = end;
    }

    public String getStart() {
        return this.start;
    }

    public String getEnd() {
        return this.end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.start + " to: " + this.end + ")";
    }
}
