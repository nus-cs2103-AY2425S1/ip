package delta.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import delta.exception.DeltaException;

/**
 * Subclass of Task class.
 * Includes the name of the event, start date/time of the event and the date/time when the event ends.
 */
public class Event extends Task {
    // Date/time format used for printing.
    protected static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MMM d yyyy ha");
    protected LocalDateTime start;
    protected LocalDateTime end;

    /**
     * Creates an Event instance.
     * Only used by load method in Storage class to load up saved tasks.
     *
     * @param description Name of event task.
     * @param start Start date/time of event.
     * @param end End date/time of event.
     * @throws DeltaException If time/date retrieved has the wrong format (i.e. save file corrupted).
     */
    public Event(String description, String start, String end) throws DeltaException {
        super(description, TaskType.Event);
        try {
            this.start = LocalDateTime.parse(start, FORMATTER);
            this.end = LocalDateTime.parse(end, FORMATTER);
        } catch (DateTimeParseException e) {
            throw new DeltaException("OOPS!!! Save File has been corrupted!");
        }
    }

    /**
     * Overloaded constructor for Deadline instance.
     *
     * @param description Name of event task.
     * @param start Start date/time of event stored as LocalDateTime object.
     * @param end End date/time of event stored as LocalDateTime object.
     */
    public Event(String description, LocalDateTime start, LocalDateTime end) {
        super(description, TaskType.Event);
        this.start = start;
        this.end = end;
    }

    public String getStart() {
        return start.format(FORMATTER);
    }

    public String getEnd() {
        return end.format(FORMATTER);
    }

    public LocalDateTime getStartUnformatted() {
        return start;
    }

    public LocalDateTime getEndUnformatted() {
        return end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + getStart() + " to: " + getEnd() + ")";
    }

    /**
     * Formats Event for saving.
     *
     * @return Formatted string containing details of Event.
     */
    @Override
    public String saveDetails() {
        return "E | " + super.saveDetails() + " | " + getStart() + " | " + getEnd();
    }
}
