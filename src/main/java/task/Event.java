package task;

/**
 * A Task with a specific start and end time.
 *
 * @author Toh Yi Hui A0259080A
 */
public class Event extends Task {
    private String start;
    private String end;

    /**
     * Constructor for a new Event task.
     *
     * @param description the description of the task.
     * @param start the start time of the task.
     * @param end the end time of the task.
     */
    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Return the String representation of the task.
     */
    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), start, end);
    }
}
