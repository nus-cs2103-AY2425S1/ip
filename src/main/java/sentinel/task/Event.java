package sentinel.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import sentinel.parser.Parser;

/**
 * Represents an event task.
 */
public class Event extends Task {
    private final LocalDate startTime;
    private final LocalDate endTime;

    /**
     * Constructor for event.
     *
     * @param description Description for the event.
     * @param startTime Start time for the event.
     * @param endTime End time for the event.
     */
    public Event(String description, LocalDate startTime, LocalDate endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + String.format(" (from: %s to: %s)",
                startTime.format(DateTimeFormatter.ofPattern(Parser.DATE_OUTPUT_PATTERN)),
                endTime.format(DateTimeFormatter.ofPattern(Parser.DATE_OUTPUT_PATTERN)));
    }
}
