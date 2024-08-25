package diego;

/**
 * Represents an event task with a description, start time, and end time.
 */
public class Event extends Task {
    protected String start;
    protected String end;

    /**
     * Constructs a new Event task.
     *
     * @param description The description of the event task.
     * @param start       The start time/date of the event.
     * @param end         The end time/date of the event.
     */
    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns a string representation of the event task.
     *
     * @return A string describing the event task.
     */
    @Override
    public String toString() {
        return "[E]" + (isDone ? "[X]" : "[ ]") + " " + description + " (from: " + start + " to: " + end + ")";
    }

    /**
     * Returns the file format string of the event task for storage.
     *
     * @return A string suitable for storing in a file.
     */
    @Override
    public String toFileFormat() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + start + " | " + end;
    }
}
