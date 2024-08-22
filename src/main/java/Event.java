/**
 * Represents a task with a specific start date and end date.
 */
public class Event extends Task{
    private String start;
    private String end;
    /**
     * Constructs an event task with the given description, start date, and end date.
     *
     * @param description The description of the task.
     * @param start The start date of the event.
     * @param end The end date of the event.
     */
    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns a string representation of the event task.
     *
     * @return A string in the format "[E] description (by: start to: end)".
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (by: " + this.start + " to: " + this.end + ")";
    }
}
