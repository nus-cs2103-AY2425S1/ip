package papadom.tasks;

/**
 * Represents an event task.
 * An event task contains a description, a start time, and an end time.
 */
public class Event extends Task {
    protected String from;
    protected String to;
    /**
     * Constructs an Event task with a description, start time, and end time.
     *
     * @param description The description of the event task.
     * @param from The start time of the event.
     * @param to The end time of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string representation of the event task, including the start and end times.
     *
     * @return A string representing the event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
