package pebble;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Subclass of Tasks that has a deadline.
 */
public class Deadline extends Task {
    /** When deadline date is properly formatted */
    protected LocalDate by;
    /** When deadline date cannot be formatted from string will be left as string */
    protected String stringBy;

    /**
     *  Constructor for Deadline.
     *
     * @param description Task description.
     * @param by Deadline date.
     */
    public Deadline(String description, String by) {
        super(description);
        try {
            this.by = LocalDate.parse(by.trim());
        } catch (DateTimeException e) {
            this.stringBy = by;
        } catch (NullPointerException e) {
            this.stringBy = "null";
        }

    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + (by != null ? by.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
                : stringBy)
                + ")";
    }
}
