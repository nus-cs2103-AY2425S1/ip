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
     * @param startDate   Start datetime of the event.
     * @param endDate     End datetime of the event.
     */
    public Event(String description, LocalDateTime startDate, LocalDateTime endDate) {
        super(description);
        this.startDate = startDate;
        this.endDate = endDate;
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
