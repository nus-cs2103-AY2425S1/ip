package pebble;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Subclass of Tasks that has a start and end date.
 */
public class Event extends Task {
    /** When start date is properly formatted */
    protected LocalDate from;
    /** When end date is properly formatted */
    protected LocalDate to;
    /** When start date cannot be formatted from string will be left as string */
    protected String stringFrom;
    /** When end date cannot be formatted from string will be left as string */
    protected String stringTo;

    /**
     * Constructor for event instance
     *
     * @param description Description of event.
     * @param from Start date/time.
     * @param to End date/time.
     */

    public Event(String description, String from, String to) {
        super(description);
        try {
            this.from = LocalDate.parse(from.trim());
        } catch (DateTimeException e) {
            this.stringFrom = from;
        } catch (NullPointerException e) {
            this.stringFrom = "null";
        }
        try {
            this.to = LocalDate.parse(to.trim());
        } catch (DateTimeException e) {
            this.stringTo = to;
        } catch (NullPointerException e) {
            this.stringTo = "null";
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: "
                + (from != null ? from.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) : stringFrom)
                + " to: "
                + (to != null ? to.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) : stringTo)
                + ")";
    }
}
