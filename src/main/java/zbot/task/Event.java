package zbot.task;

import java.time.LocalDateTime;

import zbot.Parser;

/**
 * Represents an event with a description, start and end datetime.
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructor for Event.
     *
     * @param description Description of the event.
     * @param from        Start datetime of the event.
     * @param to          End datetime of the event.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the start datetime of the event.
     *
     * @return Start datetime of the event.
     */
    public LocalDateTime getFrom() {
        return from;
    }

    /**
     * Returns the end datetime of the event.
     *
     * @return End datetime of the event.
     */
    public LocalDateTime getTo() {
        return to;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(),
                Parser.formatDateTimeToOutput(from), Parser.formatDateTimeToOutput(to));
    }
}
