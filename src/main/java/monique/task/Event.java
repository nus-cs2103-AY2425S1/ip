package monique.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The <code>Event</code> class represents a task with a specific time range.
 * It extends the <code>Task</code> class and includes a start date and an end date for the event.
 */
public class Event extends Task {
    public static final DateTimeFormatter DATE_TIME_FORMAT_DATE_ONLY = DateTimeFormatter.ofPattern("MMM d yyyy");
    public static final DateTimeFormatter DATE_TIME_FORMAT_DATE_AND_TIME =
            DateTimeFormatter.ofPattern("MMM d yyyy h:[mm]a");
    private static final String FORMATSTRING = "[E][%s] %s (from:%s to: %s)";
    private final LocalDateTime from;
    private final LocalDateTime to;
    private final boolean hasTime;

    /**
     * Constructs a new <code>Event</code> object with the specified description,
     * completion status, start date, and end date.
     *
     * @param description The description of the event.
     * @param isComplete The completion status of the event.
     * @param from The start date of the event.
     * @param to The end date of the event.
     */
    public Event(String description, boolean isComplete, LocalDateTime from, LocalDateTime to, boolean hasTime) {
        super(description, isComplete);
        this.from = from;
        this.to = to;
        this.hasTime = hasTime;
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
        return String.format(FORMATSTRING, this.isComplete() ? "X" : " " , this.getDescription(),
                this.hasTime ? this.from.format(DATE_TIME_FORMAT_DATE_AND_TIME)
                        : this.from.format(DATE_TIME_FORMAT_DATE_ONLY),
                this.hasTime ? this.to.format(DATE_TIME_FORMAT_DATE_AND_TIME)
                        : this.to.format(DATE_TIME_FORMAT_DATE_ONLY));
    }

    /**
     * Marks the <code>Event</code> task as complete and returns a new <code>Event</code> object
     * with the updated status.
     *
     * @return A new <code>Event</code> object with the status marked as complete.
     */
    @Override
    public Event mark() {
        return new Event(this.getDescription(), true , this.from, this.to, this.hasTime);
    }

    /**
     * Unmarks the <code>Event</code> task as incomplete and returns a new <code>Event</code> object
     * with the updated status.
     *
     * @return A new <code>Event</code> object with the status marked as incomplete.
     */
    @Override
    public Event unmark() {
        return new Event(this.getDescription(), false, this.from, this.to, this.hasTime);
    }

    /**
     * Returns the start date of the <code>Event</code>.
     *
     * @return The start date of the event.
     */
    public LocalDateTime getFrom() {
        return this.from;
    }

    /**
     * Returns the end date of the <code>Event</code>.
     *
     * @return The end date of the event.
     */
    public LocalDateTime getTo() {
        return this.to;
    }
}
