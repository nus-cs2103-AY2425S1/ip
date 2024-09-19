package megamind.task;

import java.io.Serial;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * The `Event` class represents an event task.
 * It extends the `Task` class and includes additional information about the start and end times of the event.
 * The class provides methods to construct an event task and return its string representation.
 */
public class Event extends Task {
    @Serial
    private static final long serialVersionUID = 1L;
    private final LocalDateTime start;
    private final LocalDateTime end;

    /**
     * Constructor for the Event class.
     *
     * @param description Description of the task.
     * @param start       Start time of the event.
     * @param end         End time of the event.
     * @throws DateTimeParseException If the deadline is not in the correct format.
     */
    public Event(String description, String start, String end) throws DateTimeParseException {
        super(description);
        this.start = LocalDateTime.parse(start, INPUT_FORMATTER);
        this.end = LocalDateTime.parse(end, INPUT_FORMATTER);
    }

    /**
     * Returns the String representation of the task.
     *
     * @return String representation of the task
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (FROM: " + this.start.format(OUTPUT_FORMATTER)
               + " TO: " + this.end.format(OUTPUT_FORMATTER) + ")";
    }
}
