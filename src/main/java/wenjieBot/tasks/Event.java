package wenjieBot.tasks;

/**
 * The Event class represents an event task in the wenjieBot application.
 * It extends the Task class and includes additional information for the event,
 * specifically the start and end times.
 */
public class Event extends Task {

    protected String from;
    protected String to;

    /**
     * Constructs an Event with the specified description, start time, and end time.
     *
     * @param description The description of the event.
     * @param from The start time of the event.
     * @param to The end time of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string representation of the Event in a format suitable for storage.
     * The format includes the type of task, description, start time, and end time.
     *
     * @return A string representation of the Event for storage.
     */
    @Override
    public String toPrettierString() {
        return "E" + super.toPrettierString() + "/from: " + from + "/to: " + this.to;
    }

    /**
     * Returns a string representation of the Event for display purposes.
     * The format includes the type of task, description, start time, and end time.
     *
     * @return A string representation of the Event for display.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + from + " to: " + this.to + ")";
    }
}
