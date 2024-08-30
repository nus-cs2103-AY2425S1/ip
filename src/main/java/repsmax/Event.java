package repsmax;

/**
 * Represents a task that occurs within a specific time frame.
 * An <code>Event</code> object corresponds to a task with a start and end time.
 */
public class Event extends Task {

    /** The start date/time of the event. */
    protected String from;

    /** The end date/time of the event. */
    protected String to;

    /**
     * Constructs a new Event object with the specified description, start time, and end time.
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
     * Returns a string representation of the Event object, including its status, start, and end times.
     *
     * @return A string representing the Event object.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    /**
     * Returns a string in the file format used to save the Event object to disk.
     *
     * @return A string representing the Event object in the file format.
     */
    @Override
    public String toFileFormat() {
        return "E | " + (super.isDone() ? "1" : "0") + " | " + getDescription() + " | " + from + " | " + to;
    }
}
