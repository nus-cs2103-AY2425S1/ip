package elysia.tasks;

/**
 * Represents an event task with a specific start and end time.
 * Extends the Task class and includes the time range during which the event occurs.
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
     * Converts the Event task to a string format suitable for saving to a file.
     * The format is "E" followed by the format from Task, then two separators "|",
     * with the start time and end time.
     *
     * @return A string representation of the Event task in file format.
     */
    @Override
    public String toFile() {
        return "E" + super.toFile() + "|" + from + "|" + to;
    }

    /**
     * Returns a string representation of the Event task, including its description,
     * start time, and end time. The format is "[E]" followed by the format from Task,
     * then "(from: start time to: end time)".
     *
     * @return A string representation of the Event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}