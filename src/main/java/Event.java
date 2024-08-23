/**
 * The {@code Event} class represents a task that occurs at a specific time
 * with a defined start and end time. It is a subclass of {@link Task}.
 */
public class Event extends Task {

    /** The start time of the event. */
    protected String start;

    /** The end time of the event. */
    protected String end;

    /**
     * Constructs a new {@code Event} task with the specified description, start time, and end time.
     * The task type is set to {@link TaskType#EVENT}.
     *
     * @param description The description of the event task.
     * @param start       The start time of the event.
     * @param end         The end time of the event.
     */
    public Event(String description, String start, String end) {
        super(description, TaskType.EVENT);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns a string representation of the event task,
     * including its type indicator, description, start time, and end time.
     *
     * @return A string in the format: "[E][status] description (from: start to: end)".
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start + " to: " + end + ")";
    }
}
