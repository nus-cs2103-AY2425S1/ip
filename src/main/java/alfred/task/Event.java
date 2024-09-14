package alfred.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import alfred.exception.AlfredException;

/**
 * Represents an event task with a description, start date, and end date.
 * This class extends the <code>Task</code> class to include specific handling
 * for tasks that have a date range.
 */
public class Event extends Task {
    private static final DateTimeFormatter EVENT_DATE_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy");
    private LocalDate from;
    private LocalDate to;

    /**
     * Constructs a new <code>Events</code> task with the specified description, start date, and end date.
     * The dates should be in the format yyyy-mm-dd. If the dates are invalid, an <code>AlfredException</code>
     * is thrown.
     *
     * @param description Description of the event.
     * @param from Start date of the event in yyyy-mm-dd format.
     * @param to End date of the event in yyyy-mm-dd format.
     * @throws AlfredException If the date format is invalid.
     */
    public Event(String description, String from, String to) throws AlfredException {
        super(description);
        this.from = parseDate(from);
        this.to = parseDate(to);
    }

    /**
     * Constructs a new <code>Events</code> task with the specified description, start date, end date,
     * and completion status. The dates should be in the format yyyy-mm-dd. If the dates are invalid,
     * an <code>AlfredException</code> is thrown.
     *
     * @param description Description of the event.
     * @param from Start date of the event in yyyy-mm-dd format.
     * @param to End date of the event in yyyy-mm-dd format.
     * @param isDone Completion status of the task.
     * @param tags A list of tags associated with the task.
     * @throws AlfredException If the date format is invalid.
     */
    public Event(String description, String from, String to,
                 boolean isDone, List<String> tags) throws AlfredException {
        super(description, isDone, tags);
        this.from = parseDate(from);
        this.to = parseDate(to);
    }

    /**
     * Validates and parses a date string into a LocalDate object.
     * @param date The date string in yyyy-mm-dd format.
     * @return A LocalDate object representing the date.
     * @throws AlfredException If the date format is invalid.
     */
    private static LocalDate parseDate(String date) throws AlfredException {
        try {
            return LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            throw new AlfredException("There was an invalid deadline Sir. It should go yyyy-mm-dd.");
        }
    }

    /**
     * Returns a string representation of the <code>Events</code> task, including its status, description,
     * start date, and end date. The dates are formatted as "MMM d yyyy".
     *
     * @return A string representing the <code>Events</code> task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + formatDate(from) + " to: "
                + formatDate(to) + ")";
    }

    /**
     * Formats a LocalDate object into a string with the format MMM d yyyy.
     * @param date The LocalDate to be formatted.
     * @return A formatted string representing the date.
     */
    private static String formatDate(LocalDate date) {
        return date.format(EVENT_DATE_FORMAT);
    }

    /**
     * Returns the file format representation of the <code>Events</code> task.
     * The format is "E | status | description | from | to", where status is the task's
     * completion status, description is the task's description, from is the start date, and
     * to is the end date.
     *
     * @return A string representing the <code>Events</code> task in file format.
     */
    @Override
    public String toFileFormat() {
        return "E | " + getStatusIcon() + " | " + getTagsAsString() + " | " + description
                + " | " + from + " | " + to;
    }
}
