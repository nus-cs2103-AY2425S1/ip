package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.exceptions.InvalidEventException;
import duke.parser.DateTimeFormatEnum;

/**
 * Represents an event task in the DailyTasks application.
 * An event task includes a description and a time range specified by a 'from' and 'to' datetime.
 */
public class Event extends Task {

    /** The starting datetime of the event. */
    protected LocalDateTime parsedFromDateTime;

    /** The ending datetime of the event. */
    protected LocalDateTime parsedToDateTime;

    /**
     * Creates a new Event task with the specified description, start ('from'), and end ('to') times.
     * Validates that the 'from' and 'to' datetimes are correctly formatted and that 'from' is before 'to'.
     *
     * @param description The description of the event.
     * @param from The start datetime of the event in string format.
     * @param to The end datetime of the event in string format.
     * @throws InvalidEventException If the 'from' or 'to' datetimes are invalid or 'from' is after 'to'.
     */
    public Event(String description, String from, String to) throws InvalidEventException {
        super(description);

        parsedFromDateTime = null;
        parsedToDateTime = null;

        // Parse the 'from' field
        parsedFromDateTime = DateTimeFormatEnum.parse(from)
                .orElseThrow(() -> new IllegalArgumentException("Invalid date format."));
        if (parsedFromDateTime == null) {
            throw new InvalidEventException("Invalid 'from' date and time format.");
        }

        // Parse the 'to' field
        parsedToDateTime = DateTimeFormatEnum.parse(to)
                .orElseThrow(() -> new IllegalArgumentException("Invalid date format."));
        if (parsedToDateTime == null) {
            throw new InvalidEventException("Invalid 'to' date and time format.");
        }

        // Ensure that 'from' is before 'to'
        if (parsedFromDateTime.isAfter(parsedToDateTime)) {
            throw new InvalidEventException("'From' date and time must be before 'to' date and time.");
        }
    }

    /**
     * Returns the formatted start datetime of the event.
     *
     * @return The formatted start datetime as a string (e.g., "MMM d yyyy, h:mm a").
     */
    public String getFrom() {
        return parsedFromDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a"));
    }

    /**
     * Returns the formatted end datetime of the event.
     *
     * @return The formatted end datetime as a string (e.g., "MMM d yyyy, h:mm a").
     */
    public String getTo() {
        return parsedToDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a"));
    }

    /**
     * Checks if the event is occurring at a given datetime.
     *
     * @param taskDate The datetime to check.
     * @return true if the event is occurring at the given datetime, false otherwise.
     */
    @Override
    public boolean occurring(LocalDateTime taskDate) {
        return (
                taskDate != null && taskDate.isAfter(this.parsedFromDateTime)
                        && taskDate.isBefore(this.parsedToDateTime)
            );
    }

    /**
     * Returns a string representation of the event task, including its type identifier,
     * status icon, description, and the formatted 'from' and 'to' datetimes.
     *
     * @return The string representation of the event task.
     */
    @Override
    public String toString() {
        return "[E]" + " [" + this.getStatusIcon()
                + "] " + "[Priority: " + this.priority + "] "
                + super.toString()
                + " (from: " + this.getFrom()
                + " to: " + this.getTo() + ")";
    }
}
