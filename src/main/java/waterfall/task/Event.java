package waterfall.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a specific <code>Task</code> type known as <code>Event</code>.
 * The <code>Event</code> object consists of a title, start and end time.
 * It consists of methods for formatting and displaying the start and end.
 *
 * @author Wai Hong
 */
public class Event extends Task {
    private final LocalDateTime start;
    private final LocalDateTime end;

    /**
     * Constructs an Event object.
     *
     * @param title The title of the event.
     * @param start The start time of the event.
     * @param end The end time of the event.
     */
    public Event(String title, String start, String end) {
        super(title);
        this.start = parseDateTime(start);
        this.end = parseDateTime(end);
    }

    /**
     * Parses the date time string into a LocalDateTime object.
     *
     * @param dateString Date time in string format.
     * @return LocalDateTime object with the specified date time.
     */
    private LocalDateTime parseDateTime(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return LocalDateTime.parse(dateString, formatter);
    }

    /**
     * Formats the LocalDateTime object into storage date time.
     *
     * @param time The LocalDateTime object to be formatted.
     * @return A date time string to be stored in database.
     */
    private String getFormattedTime(LocalDateTime time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return time.format(formatter);
    }

    /**
     * Formats the LocalDateTime object into displayable string.
     *
     * @param time The LocalDateTime object to be formatted.
     * @return A date time string to be displayed.
     */
    private String getBeautifulTime(LocalDateTime time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy HH:mm");
        return time.format(formatter);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + getBeautifulTime(start) + " to: " + getBeautifulTime(end) + ")";
    }

    @Override
    public String toStorageString() {
        if (this.isDone()) {
            return "E | 1 | " + this.getTitle() + " | " + getFormattedTime(start) + " | " + getFormattedTime(end);
        } else {
            return "E | 0 | " + this.getTitle() + " | " + getFormattedTime(start) + " | " + getFormattedTime(end);
        }
    }
}
