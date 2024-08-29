import java.time.LocalDateTime;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    private final DateTimeParser dateTimeParser = new DateTimeParser();

    /**
     * Constructor for Event class.
     * @param description the description of the task/event
     * @param from when the event begins
     * @param to when the event ends
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) throws IllegalArgumentException {
        super(description);
        if (from == null || to == null) {
            throw new IllegalArgumentException("Argument cannot be null.");
        }
        this.from = from;
        this.to = to;
    }

    @Override
    public String toStringSave() {
        int indicator = this.isDone ? 1 : 0;
        return "E | " + indicator + " | " + this.description
                + " | " + this.from + " | " + this.to;
    }

    /**
     * Returns a string representation of the task.
     * @return task description with status and event period
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from " + this.dateTimeParser.formatOutput(this.from)
                + " to " + this.dateTimeParser.formatOutput(this.to) + ")";
    }
}
