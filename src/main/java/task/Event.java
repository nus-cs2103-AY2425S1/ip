package task;

/**
 * The Event class represents a Task that happens within a time period
 */
public class Event extends Task {
    private String from;
    private String to;

    /**
     * Constructor for a Event
     * @param description String representing the task description
     * @param from String representing the start date
     * @param to String representing the end date
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }
}
