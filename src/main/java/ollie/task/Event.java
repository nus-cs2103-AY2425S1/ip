package ollie.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import ollie.exception.OllieException;

/**
 * Represents an event task. It contains 2 dates, with each representing
 * when the event starts from, and when the event last to, respectively.
 */
public class Event extends Task {

    private LocalDate from;
    private LocalDate to;

    /**
     * Constructs an event object which represents an event task which has an additional from date and to date.
     */
    public Event(String description, LocalDate from, LocalDate to) throws OllieException {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " to: " + this.to.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * Return the formatted string for easy parsing and writing into file (database).
     *
     * @return Formatted String.
     */
    public String getFormattedString() {
        return "E | " + super.getFormattedString() + " | " + this.from + " | " + this.to;
    }
}
