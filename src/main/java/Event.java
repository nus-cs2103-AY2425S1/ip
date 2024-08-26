import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The Event class represents a task that occurs during a specific time frame.
 * It extends the Task class by adding a start and end time.
 */
public class Event extends Task {
    protected LocalDateTime start, end;

    /**
     * Constructs a new Event with the given description, start time, and end time.
     *
     * @param description The description of the event.
     * @param start The start time of the event.
     * @param end The end time of the event.
     */
    public Event(String description, String start, String end) {
        super(description);
        this.start = LocalDateTime.parse(start, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        this.end = LocalDateTime.parse(end, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    @Override
    public boolean occursOn(LocalDate date) {
        return this.start.toLocalDate().equals(date) || this.end.toLocalDate().equals(date) ||
                (date.isAfter(ChronoLocalDate.from(start)) && date.isBefore(ChronoLocalDate.from(end)));
    }

    /**
     * Returns a string representation of the event, including its status icon, description,
     * start time, and end time.
     *
     * @return A string in the format "[E][statusIcon] description (from: start to: end)".
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " +
                start.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a")) + " to: " +
                end.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a")) + ")";
    }
}

