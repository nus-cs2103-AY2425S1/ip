package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    private final LocalDateTime startDateTime;
    private final LocalDateTime endDateTime;
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");

    /**
     * Constructor for task.Event class.
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
     * The string is in the format of "E | 1 | description | startDateTime | endDateTime".
     * E is the task type, 1 is 1 if the task is done, description is the description of the task,
     * startDateTime is the start date and time of the event, endDateTime is the end date and time of the event.
     * The task type, is done status, description, start date and time and end date and time are separated by " | ".
     * The task type is represented by the first character of the task type.
     * For example, if the task type is task.Event, the task type is E.
     * If the task is done, the second character is 1, otherwise it is 0.
     * The description, start date and time and end date and time are the description, start date and time and end date and time of the task respectively.
     * The task type, is done status, description, start date and time and end date and time are separated by " | ".
     *
     * @return Serialized event.
     */
    @Override
    public String serialize() {
        return super.serialize() + " | " + startDateTime.format(INPUT_FORMATTER) + " | " + endDateTime.format(INPUT_FORMATTER);
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
