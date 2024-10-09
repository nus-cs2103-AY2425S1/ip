package tasks;

/**
 * Represents an event task. An Event task has a description, a start time, and an end time.
 */
public class Event extends Task {
    private static final String TASK_TYPE = "event";
    private String from;
    private String to;

    /**
     * Constructs an Event task with the specified description, start time, and end time.
     *
     * @param description The description of the event.
     * @param from        The start time of the event.
     * @param to          The end time of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string representation of the Event task, including its status, description, start time, and end time.
     *
     * @return A string representation of the event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    /**
     * Returns a string format of the Event task for saving to a file.
     *
     * @return A string formatted for saving to a file.
     */
    @Override
    public String toFileFormat() {
        return "E | " + super.toFileFormat() + " | " + from + " | " + to;
    }

    /**
     * Returns the type of the task as a string ("event").
     *
     * @return The string "event" representing the task type.
     */
    @Override
    public String getTaskType() {
        return TASK_TYPE;
    }

    /**
     * Retrieves the start time of the event.
     *
     * @return The start time of the event as a string.
     */
    public String getFrom() {
        return this.from;
    }

    /**
     * Retrieves the end time of the event.
     *
     * @return The end time of the event as a string.
     */
    public String getTo() {
        return this.to;
    }

}
