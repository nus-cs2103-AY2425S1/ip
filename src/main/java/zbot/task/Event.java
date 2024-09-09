package zbot.task;

import java.time.LocalDateTime;

import zbot.Parser;

/**
 * Represents an event with a description, start and end datetime.
 */
public class Event extends Task {
    protected LocalDateTime startDate;
    protected LocalDateTime endDate;

    /**
     * Constructor for Event.
     *
     * @param description Description of the event.
     * @param from        Start datetime of the event.
     * @param to          End datetime of the event.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.startDate = from;
        this.endDate = to;
    }

    /**
     * Returns the start datetime of the event.
     *
     * @return Start datetime of the event.
     */
    public LocalDateTime getStartDate() {
        return startDate;
    }

    /**
     * Returns the end datetime of the event.
     *
     * @return End datetime of the event.
     */
    public LocalDateTime getEndDate() {
        return endDate;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(),
                Parser.formatDateTimeToOutput(startDate), Parser.formatDateTimeToOutput(endDate));
    }
}
