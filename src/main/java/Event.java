/**
 * The {@code Event} class represents a task that spans a specific period of time, starting and ending at particular dates/times.
 * <p>
 * It extends the {@code Task} class and is marked with an "E" to indicate that it is an Event task.
 * </p>
 */
public class Event extends Task {
    /** The start date/time of the event. */
    protected String from;
    /** The end date/time of the event. */
    protected String to;

    /**
     * Constructs a new {@code Event} task with the specified description, start date/time, and end date/time.
     *
     * @param description The description of the Event task.
     * @param from The start date/time of the event.
     * @param to The end date/time of the event.
     */

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string representation of the {@code Event} task.
     * <p>
     * The string includes the task type identifier "[E]" followed by the status icon,
     * description, and the date/time range of the event.
     * </p>
     *
     * @return A string representation of the Event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}