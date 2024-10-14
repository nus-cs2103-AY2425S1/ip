package tasks;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Class that represents an {@code Event} with a start and end date.
 */
public class Event extends Task {
    private final LocalDateTime localDateTimeFrom;
    private final LocalDate localDateFrom;
    private final LocalDateTime localDateTimeTo;
    private final LocalDate localDateTo;
    private final String key;

    /**
     * Constructs an {@code Event} task.
     * <p>
     * Note that setting the time is optional.
     * @param description Description of the event.
     * @param from The start date (and time) of the event, as a String.
     * @param to The end date (and time) of the event, as a String.
     */
    public Event(String description, String from, String to) {
        super(description);

        localDateTimeFrom = DateHandler.parseLocalDateTime(from);
        localDateFrom = DateHandler.parseLocalDate(from);
        localDateTimeTo = DateHandler.parseLocalDateTime(to);
        localDateTo = DateHandler.parseLocalDate(to);

        if ((localDateTimeFrom == null && localDateFrom == null)
                || (localDateTimeTo == null && localDateTo == null)) {
            throw new DateTimeException("");
        }

        this.key = description + from + to;
    }

    /**
     * Returns the task icon [E], followed by its done/undone status.
     */
    @Override
    public String getStatusIcon() {
        return "[E]" + super.getStatusIcon();
    }

    /**
     * Returns the task description, followed by its start date and end date.
     * <p>
     * Time will be added if it had been previously set.
     */
    @Override
    public String toString() {
        String from;
        String to;

        if (localDateTimeFrom != null) {
            from = localDateTimeFrom.format(DateHandler.dateTimeFormat);
        } else {
            from = localDateFrom.format(DateHandler.dateFormat);
        }

        if (localDateTimeTo != null) {
            to = localDateTimeTo.format(DateHandler.dateTimeFormat);
        } else {
            to = localDateTo.format(DateHandler.dateFormat);
        }

        return super.description + " (from: " + from + " to: " + to + ") ";
    }

    /**
     * Returns the key of this {@code Event}.
     * <p>
     * The key is formed by concatenating the {@code description}, {@code from}, and
     * {@code to} arguments passed into the {@code Event} constructor.
     */
    public String getKey() {
        return this.key;
    }
}
