/**
 * Represents an Event task that starts and ends at specific date/times.
 */
public class Event extends Task {
    private final String from; // The start date/time of the event
    private final String to;   // The end date/time of the event

    /**
     * Constructs a new Event task with the specified description,
     * start date/time, and end date/time.
     *
     * @param description The description of the Event task.
     * @param from        The start date/time of the event.
     * @param to          The end date/time of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Constructs a new Event task with the specified description,
     * start date/time, and end date/time.
     *
     * @param description The description of the Event task.
     * @param from        The start date/time of the event.
     * @param to          The end date/time of the event.
     * @param isDone      The status of the event.
     */
    public Event(String description, String from, String to, boolean isDone) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    /**
     * Return the string format of Event task to be saved for storage.
     *
     * @return A specified format string for storage.
     */
    @Override
    public String toFileString() {
        return "E | " + super.toFileString() + " | " + this.from + " | " + this.to;
    }

    /**
     * Returns a string representation of the Event task,
     * including its type, status icon, description, start and end date/times.
     *
     * @return The task's type "[E]", status icon, description, start and end date/times.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
