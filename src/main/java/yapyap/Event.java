package yapyap;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an Event task with a description, start time, and end time.
 * The Event task can be marked as done or not done.
 */
public class Event extends Task {

    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Creates an Event with the specified description, start time, and end time.
     * The date-time format should be in the pattern "d/M/yyyy HHmm".
     *
     * @param description Description of the event.
     * @param from Start time of the event in the format "d/M/yyyy HHmm".
     * @param to End time of the event in the format "d/M/yyyy HHmm".
     * @throws YapperBotException If the date-time format is invalid.
     */
    public Event (String description, String from, String to) throws YapperBotException {
        super(description);
        try {
            this.from = LocalDateTime.parse(from, DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
            this.to = LocalDateTime.parse(to, DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));;
        } catch (DateTimeParseException e){
            throw new YapperBotException("Invalid date-time format! Please use the format: d/M/yyyy HHmm");
        }

    }

    /**
     * Creates an event with the specified description, start time, end time and completion status.
     * The date-time format should be in the pattern "d/M/yyyy HHmm".
     *
     * @param description Description of the event.
     * @param from Start time of the event in the format "d/M/yyyy HHmm".
     * @param to End time of the event in the format "d/M/yyyy HHmm".
     * @param isDone Completion status of the event.
     * @throws YapperBotException If the date-time format is invalid.
     */
    public Event (String description, String from, String to, boolean isDone) throws YapperBotException {
        super(description);
        this.isDone = isDone;
        try {
            this.from = LocalDateTime.parse(from, DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
            this.to = LocalDateTime.parse(to, DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));;
        } catch (DateTimeParseException e){
            throw new YapperBotException("Invalid date-time format! Please use the format: d/M/yyyy HHmm");
        }

    }

    /**
     * Returns a string representation of the event, including its status, description,
     * start time, and end time formatted as "MMM dd yyyy h:mm a".
     *
     * @return Formatted string of the event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from.format(DateTimeFormatter.ofPattern("MMM dd yyyy h:mm a"))
                + " to: " + to.format(DateTimeFormatter.ofPattern("MMM dd yyyy h:mm a")) + ")";
    }

    /**
     * Converts the event to a format suitable for saving to a file.
     * The format includes the event type, status, description, start time, and end time.
     *
     * @return Formatted string representation of the event for saving.
     */
    @Override
    public String toSaveFormat() {
        return "E | " + (this.isDone ? 1 : 0) + " | " + this.description
                + " | " + this.from.format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm"))
                + " - " + this.to.format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
    }
}
