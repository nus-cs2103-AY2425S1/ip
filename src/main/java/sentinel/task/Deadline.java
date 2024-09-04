package sentinel.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import sentinel.parser.Parser;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {
    private final LocalDate endTime;

    /**
     * Constructor for deadline.
     *
     * @param description Description for the event.
     * @param endTime End time for the event.
     */
    public Deadline(String description, LocalDate endTime) {
        super(description);
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + String.format(" (by: %s)",
                endTime.format(DateTimeFormatter.ofPattern(Parser.DATE_OUTPUT_PATTERN)));
    }
}
