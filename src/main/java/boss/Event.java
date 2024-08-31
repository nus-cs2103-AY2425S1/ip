package boss;


/**
 * Represents an Event, which is a type of task
 * users can add to their list.
 */
public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Creates an Event object
     * @param description description of task
     * @param from start date of task
     * @param to end date of task
     * @param isDone status of task
     */
    public Event(String description, String from, String to, boolean isDone) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "| from: " + from + "| to: " + to;
    }
}
