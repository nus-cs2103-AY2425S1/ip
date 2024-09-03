package broski.task;

import java.time.LocalDateTime;

import broski.exception.InvalidDateTimeException;
import broski.parser.DateTimeParser;

/**
 * Class that holds functionality for tasks with deadline.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    private final DateTimeParser dateTimeParser = new DateTimeParser();

    /**
     * Constructs a new task that has a deadline.
     * @param description description of the task
     * @param by deadline of the task
     * @throws InvalidDateTimeException if by parameter is null due to invalid user input
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

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by " + this.dateTimeParser.formatOutput(this.by) + ")";
    }
}
