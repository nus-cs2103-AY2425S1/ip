import java.io.Serial;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class Event extends Task{
    @Serial
    private static final long serialVersionUID = 1L;
    private final LocalDateTime start;
    private final LocalDateTime end;

    /**
     * Constructor for the Event class.
     *
     * @param description Description of the task.
     * @param start Start time of the event.
     * @param end End time of the event.
     * @exception DateTimeParseException If the deadline is not in the correct format.
     */
    public Event(String description, String start, String end) throws DateTimeParseException {
        super(description);
        this.start = LocalDateTime.parse(start, INPUT_FORMATTER);
        this.end = LocalDateTime.parse(end, INPUT_FORMATTER);
    }

    /**
     * Returns the String representation of the task.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (FROM: " + this.start.format(OUTPUT_FORMATTER)
                + " TO: " + this.end.format(OUTPUT_FORMATTER) + ")";
    }
}
