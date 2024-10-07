package models;

/**
 * Represents an event task in the task management system.
 * An event task has a description, a start time, and an end time.
 * It also has a completion status indicating whether the task is completed.
 *
 * <p>This class extends the {@code Task} class and adds specific functionality
 * for handling time-bound tasks.</p>
 */
public class Event extends Task {

    private String from;
    private String to;

    /**
     * Constructs an {@code Event} with the specified description.
     * The task is initialized as not completed.
     *
     * @param description The description of the event.
     */
    public Event(String description) {
        super(description);
    }

    /**
     * Constructs an {@code Event} with the specified description, start time, and end time.
     * The task is initialized as not completed.
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
     * Constructs an {@code Event} with the specified description, completion status, start time, and end time.
     *
     * @param description The description of the event.
     * @param isDone The completion status of the event; {@code true} if the event is completed, {@code false} otherwise.
     * @param from The start time of the event.
     * @param to The end time of the event.
     */
    public Event(String description, boolean isDone, String from, String to) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the start time of the event.
     *
     * @return The start time of the event.
     */
    private String getFrom() {
        return this.from;
    }

    /**
     * Returns the end time of the event.
     *
     * @return The end time of the event.
     */
    private String getTo() {
        return this.to;
    }

    /**
     * Serializes the event into a string format for saving in the database.
     * The format is "E|<isDone>|<description>|<from>|<to>", where {@code isDone} is 1 if the event is completed, and 0 otherwise.
     *
     * @return A serialized string representation of the event.
     */
    public String serialize() {
        return String.format("E|%s|%s|%s|%s", this.getIsDone() ? "1" : "0", this.getDescription(),
                this.getFrom(), this.getTo());
    }

    /**
     * Returns the string representation of the event.
     * The format is "[E][<statusIcon>] <description> (from: <from> to: <to>)", where {@code statusIcon} is "X" if the event is completed, or a space otherwise.
     *
     * @return A string representing the event for display.
     */
    @Override
    public String toString() {
        return String.format("[E][%s] %s (from: %s to: %s)", this.getStatusIcon(), this.getDescription(),
                this.getFrom(), this.getTo());
    }
}
