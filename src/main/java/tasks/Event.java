package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task that occurs over a period of time, defined by a start and end date.
 */
public class Event extends Task {

    /** The start date of the event. */
    protected LocalDate start;

    /** The end date of the event. */
    protected LocalDate end;

    /**
     * Constructs an Event task with the specified description, start date, and end date.
     *
     * @param description The description of the event.
     * @param start The start date of the event in the format "yyyy-MM-dd".
     * @param end The end date of the event in the format "yyyy-MM-dd".
     * @throws EventException If the start or end date cannot be parsed.
     */
    public Event(String description, String start, String end) throws EventException {
        super(description);

        // Parse datetime
        try {
            this.start = LocalDate.parse(start);
        } catch (DateTimeParseException e) {
            throw new EventException("Cannot parse start date");
        }

        try {
            this.end = LocalDate.parse(end);
        } catch (DateTimeParseException e) {
            throw new EventException("Cannot parse end date");
        }
    }

    /**
     * Returns a formatted string representation of the event for storage.
     *
     * @return A string representing the event in a format suitable for storage.
     */
    @Override
    public String toDataFormat() {
        return "event | " + super.toDataFormat() + " | "
                + start + " | " + end;
    }

    /**
     * Returns a formatted string representation of the event for display,
     * including its status icon, description, start, and end dates.
     *
     * @return A string representing the event with its details.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + start.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + " to: "
                + end.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + ")";
    }
}
