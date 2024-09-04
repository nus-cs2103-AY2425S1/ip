package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an event task.
 */
public class Event extends Task {
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");

    private final LocalDateTime startDateTime;
    private final LocalDateTime endDateTime;

    /**
     * Constructor for Event class.
     *
     * @param description   Description of the task.
     * @param startDateTime Start date and time of the event.
     * @param endDateTime   End date and time of the event.
     * @throws DateTimeParseException If the date and time format is invalid.
     */
    public Event(String description, String startDateTime, String endDateTime) throws DateTimeParseException {
        super(TaskType.EVENT, description);
        this.startDateTime = LocalDateTime.parse(startDateTime, INPUT_FORMATTER);
        this.endDateTime = LocalDateTime.parse(endDateTime, INPUT_FORMATTER);
    }

    /**
     * Returns the start date and time of the event.
     *
     * @return Start date and time of the event.
     */
    public String getStartDateTime() {
        return startDateTime.format(OUTPUT_FORMATTER);
    }

    /**
     * Returns the end date and time of the event.
     *
     * @return End date and time of the event.
     */
    public String getEndDateTime() {
        return endDateTime.format(OUTPUT_FORMATTER);
    }

    /**
     * Serializes the event to a string.
     *
     * @return Serialized event.
     */
    @Override
    public String serialize() {
        return super.serialize() + " | " + startDateTime.format(INPUT_FORMATTER) + " | "
                + endDateTime.format(INPUT_FORMATTER);
    }

    /**
     * Returns the string representation of the event.
     *
     * @return String representation of the event.
     */
    @Override
    public String toString() {
        return super.toString() + " (from: " + getStartDateTime() + " to: " + getEndDateTime() + ")";
    }
}
