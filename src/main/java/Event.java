/**
 * Represents an event task.
 * This class extends the Task class, adding the from and to attributes to it.
 */
public class Event extends Task {
    /** The start time of the event. */
    protected String from;
    /** The end time of the event. */
    protected String to;

    /**
     * Constructs a new Event task.
     *
     * @param description The description of the task.
     * @param from The start time of the event.
     * @param to The end time of the event.
     */
    public Event(String description, boolean isDone, String from, String to) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    @Override
    public String getTaskType() {
        return "E";
    }

    @Override
    public String toString() {
        return super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
