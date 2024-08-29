package monique.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import monique.exception.IllegalDateFormatException;


/**
 * The <code>Event</code> class represents a task with a specific time range.
 * It extends the <code>Task</code> class and includes a start date and an end date for the event.
 */
public class Event extends Task {
    private static final String formatString = "[E][%s] %s (from:%s to: %s)";
    private final LocalDate from;
    private final LocalDate to;

    /**
     * Constructs a new <code>Event</code> object with the specified description,
     * completion status, start date, and end date in string format. The dates are parsed
     * from the strings using multiple acceptable formats.
     *
     * @param description The description of the event.
     * @param isComplete The completion status of the event.
     * @param from The start date of the event in string format.
     * @param to The end date of the event in string format.
     * @throws IllegalDateFormatException If any of the date strings do not match any of the accepted formats.
     */
    public Event(String description, boolean isComplete, String from, String to) throws IllegalDateFormatException {
        super(description , isComplete);

        LocalDate parsedFromDate = null;
        LocalDate parsedToDate = null;
        List<DateTimeFormatter> formatters = List.of(
                DateTimeFormatter.ofPattern("M/d/yyyy HHmm"),
                DateTimeFormatter.ofPattern("M/d/yyyy"),
                DateTimeFormatter.ofPattern("M-d-yyyy HHmm"),
                DateTimeFormatter.ofPattern("M-d-yyyy")
        );
        for (DateTimeFormatter formatter: formatters) {
            try {
                parsedFromDate = LocalDate.parse(from, formatter);
                break;
            } catch (DateTimeParseException e) {
                //continue to next formatter
            }
        }
        for (DateTimeFormatter formatter: formatters) {
            try {
                parsedToDate = LocalDate.parse(to, formatter);
                break;
            } catch (DateTimeParseException e) {
                //continue to next formatter
            }
        }

        if (parsedFromDate == null || parsedToDate == null) {
            throw new IllegalDateFormatException();
        }

        this.from = parsedFromDate;
        this.to = parsedToDate;

    }

    /**
     * Constructs a new <code>Event</code> object with the specified description,
     * completion status, start date, and end date.
     *
     * @param description The description of the event.
     * @param isComplete The completion status of the event.
     * @param from The start date of the event.
     * @param to The end date of the event.
     */
    public Event(String description, boolean isComplete, LocalDate from, LocalDate to) {
        super(description, isComplete);
        this.from = from;
        this.to = to;
    }

    /**
     * Constructs a new <code>Event</code> object with default values: an empty description,
     * the event marked as complete, and empty date strings.
     *
     * @throws IllegalDateFormatException If the default date strings are invalid (which they will be in this case).
     */
    public Event() throws IllegalDateFormatException {
        this("", true, "", "");
    }

    /**
     * Returns a string representation of the <code>Event</code> task.
     * The format is: "[E][status] description (from: start_date to: end_date)" where status is "X" if the
     * task is complete,
     * and the dates are formatted as "MMM d yyyy".
     *
     * @return A string representation of the event.
     */
    @Override
    public String toString() {
        return String.format(formatString , this.isComplete ? "X" : " " , this.description,
                this.from.format(DateTimeFormatter.ofPattern("MMM d yyyy")),
                this.to.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }

    /**
     * Marks the <code>Event</code> task as complete and returns a new <code>Event</code> object
     * with the updated status.
     *
     * @return A new <code>Event</code> object with the status marked as complete.
     */
    @Override
    public Event mark() {
        return new Event(this.description, true , this.from, this.to);
    }

    /**
     * Unmarks the <code>Event</code> task as incomplete and returns a new <code>Event</code> object
     * with the updated status.
     *
     * @return A new <code>Event</code> object with the status marked as incomplete.
     */
    @Override
    public Event unmark() {
        return new Event(this.description, false, this.from, this.to);
    }

    /**
     * Returns the start date of the <code>Event</code>.
     *
     * @return The start date of the event.
     */
    public LocalDate getFrom() {
        return this.from;
    }

    /**
     * Returns the end date of the <code>Event</code>.
     *
     * @return The end date of the event.
     */
    public LocalDate getTo() {
        return this.to;
    }
}
