package shenhe.task;

/**
 * Represents an event task with a specific start and end time.
 * This class extends {@link Task} to include details about the event's duration.
 */
public class Event extends Task {
    /** The start time of the event. */
    protected String from;

    /** The end time of the event. */
    protected String to;

    /**
     * Constructs an Event with the specified description, status, start time, and end time.
     *
     * @param description the description of the event
     * @param isDone      the status of the event (true if done, false otherwise)
     * @param from        the start time of the event
     * @param to          the end time of the event
     */
    public Event(String description, boolean isDone, String from, String to) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string representation of the event task in a format suitable for saving to a file.
     * The format includes the task type ("E"), status icon, description, start time, and end time.
     *
     * @return a string representation of the event task in file format
     */
    @Override
    public String toFileFormat() {
        return "E | " + getStatusIcon() + " | " + description + " | " + from + " | " + to;
    }

    /**
     * Returns a string representation of the event task.
     * The string includes the task type, status icon, description, start time, and end time.
     *
     * @return a string representation of the event task
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from " + from + " to " + to + ")";
    }
}
