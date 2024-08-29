package gavinchatbot.task;

/**
 * Represents an event task with a start time and an end time.
 */
public class Event extends Task {
    protected String fromTime;
    protected String toTime;

    /**
     * Constructs an Event task with the specified description, start time, and end time.
     *
     * @param description The description of the event.
     * @param fromTime The start time of the event.
     * @param toTime The end time of the event.
     */
    public Event(String description, String fromTime, String toTime) {
        super(description);
        this.fromTime = fromTime;
        this.toTime = toTime;
    }

    /**
     * Returns the string representation of the task in the format used for saving to a file.
     *
     * @return The string representation of the task in save format.
     */
    @Override
    public String toSaveFormat() {
        return "E | " + super.toSaveFormat() + " | " + fromTime + " | " + toTime;
    }

    /**
     * Returns the string representation of the task, including the start time and end time.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + fromTime + " to: " + toTime + ")";
    }
}
