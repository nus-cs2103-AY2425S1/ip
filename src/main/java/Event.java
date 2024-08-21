public class Event extends Task{
    private final String start;
    private final String end;

    /**
     * Constructor for the Event class.
     *
     * @param description Description of the task.
     * @param start Start time of the event.
     * @param end End time of the event.
     */
    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns the String representation of the task.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.start + " to: " + this.end + ")";
    }
}
