import java.time.LocalDateTime;

public class Deadline extends Task {
    protected LocalDateTime by;

    private final DateTimeParser dateTimeParser = new DateTimeParser();

    /**
     * Constructor for Deadline class.
     * @param description the description of the task
     * @param by the deadline of the task
     */
    public Deadline(String description, LocalDateTime by) throws InvalidDateTimeException {
        super(description);
        if (by == null) {
            throw new InvalidDateTimeException("Cannot be null");
        }
        this.by = by;
    }

    @Override
    public String toStringSave() {
        int indicator = this.isDone ? 1 : 0;
        return "D | " + indicator + " | " + this.description
                + " | " + this.by;
    }

    /**
     * Returns a string representation of the task.
     * @return task description with status and deadline
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by " + this.dateTimeParser.formatOutput(this.by) + ")";
    }
}
