package assistinator;

/**
 * Represents event task
 */
public class Event extends Task {
    protected String start;
    protected String end;

    /**
     * Initialise event task
     * @param description Task description
     * @param start Start time
     * @param end End time
     */
    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Convert task to formatted string
     * @return formatted string
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + String.format(" (from: %s to: %s)", start, end);
    }

    /**
     * Generate string to print onto file
     * @return String for file
     */
    public String toFileString() {
        return String.format(
                "E | %s | %s | %s | %s", isDone ? TaskStatus.DONE : TaskStatus.NOTDONE, description, start, end
        );
    }
}
