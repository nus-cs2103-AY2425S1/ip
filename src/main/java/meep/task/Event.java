package meep.task;

/**
 * The {@code Event} class represents a task that occurs within a specific time frame.
 * It extends the {@link Task} class by adding start and end times.
 */
public class Event extends Task {

    /**
     * The start time of the event.
     */
    private final String from;

    /**
     * The end time of the event.
     */
    private final String to;

    /**
     * Constructs an {@code Event} task with the specified description, start time, and end time.
     *
     * @param description A description of the event.
     * @param from        The start time of the event.
     * @param to          The end time of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the formatted string representation of the event for saving to a file.
     * The format includes the task type, completion status, description, start time, and end time.
     *
     * @return The formatted string for saving the event.
     */
    @Override
    public String getSaveFormat() {
        return "E|" + super.getSaveFormat() + "|" + from + "|" + to;
    }

    /**
     * Returns the string representation of the event.
     * The format includes the task type, description, start time, and end time.
     *
     * @return The string representation of the event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
