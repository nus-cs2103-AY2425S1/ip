/**
 * Represents an Event task in the Angel application.
 * An Event task has a description and a time range during which the event occurs.
 */
public class Event extends Task {

    /** The start date/time of the event. */
    protected String from;

    /** The end date/time of the event. */
    protected String to;

    /**
     * Constructs a new Event task with the specified description, start date/time, and end date/time.
     *
     * @param description The description of the event.
     * @param from The start date/time of the event.
     * @param to The end date/time of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string representation of the Event task, which includes its type, status,
     * description, start date/time, and end date/time.
     *
     * @return A string representation of the Event task.
     */
    @Override
    public String toString() {
        return "[E][" + getStatusIcon() + "] " + description + " (from: " + from + " to: " + to + ")";
    }

    /**
     * Returns a string representation of the Event task in a format suitable for saving to a file.
     *
     * @return A string in the format "E | status | description | start date/time | end date/time".
     */
    @Override
    public String toSaveFormat() {
        return "E | " + super.toSaveFormat() + " | " + from + " | " + to;
    }
}
