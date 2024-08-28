package struggling.task;

/**
 * Task class stores the description, the completion state,
 * the start and end date of a task.
 */
public class Event extends Task {

    private final String from;
    private final String to;

    /**
     * Initializes an Event object.
     *
     * @param description Description of Task.
     * @param from        Start date of Task.
     * @param to          End date of Task.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), this.from, this.to);
    }

    @Override
    public String getState() {
        return String.format("E | %s | %s | %s", super.getState(), this.from, this.to);
    }
}
