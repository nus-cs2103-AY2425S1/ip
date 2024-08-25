import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Event class includes the name of the event, start date/time of the event and the date/time when the event ends.
 * Subclass of Task class.
 */
public class Event extends Task {
    protected static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MMM d yyyy ha");
    protected LocalDateTime start;
    protected LocalDateTime end;

    /**
     * Constructor for event instance.
     * Only used by loadTasks method in Delta.java main method.
     *
     * @param description Name of event task.
     * @param start Start date/time of event.
     * @param end End date/time of event.
     * @throws DeltaException If save file is corrupted and time/date retrieved has the wrong format.
     */
    public Event(String description, String start, String end) throws DeltaException {
        super(description, TaskType.Event);
        try {
            this.start = LocalDateTime.parse(start, FORMATTER);
            this.end = LocalDateTime.parse(end, FORMATTER);
        }
        catch (DateTimeParseException e) {
            throw new DeltaException("""
                    OOPS!!! Save File has been corrupted!
                    \t Please delete the save file.
                    \t Location: ./data/DeltaList.txt""");
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

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + getStart() + " to: " + getEnd() + ")";
    }

    /**
     * Formats Event for saving.
     *
     * @return String Formatted details of Event.
     */
    @Override
    public String saveDetails() {
        return "E | " + super.saveDetails() + " | " + getStart() + " | " + getEnd();
    }
}
