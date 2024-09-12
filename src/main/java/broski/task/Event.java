package broski.task;

import java.time.LocalDateTime;

import broski.exception.InvalidDateTimeException;
import broski.parser.DateTimeParser;

/**
 * Class that holds functionality for events.
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    private final DateTimeParser dateTimeParser = new DateTimeParser();

    /**
     * Constructs a new task or event that has a time period.
     * @param description the description of the task/event
     * @param from when the event begins
     * @param to when the event ends
     * @throws InvalidDateTimeException if from or to parameter is null due to invalid user input
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) throws InvalidDateTimeException {
        super(description);
        if (from == null || to == null) {
            throw new InvalidDateTimeException("Cannot be null.");
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

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from " + this.dateTimeParser.formatOutput(this.from)
                + " to " + this.dateTimeParser.formatOutput(this.to) + ")";
    }
}
