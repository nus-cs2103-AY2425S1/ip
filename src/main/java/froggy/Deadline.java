package froggy;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with description and a deadline.
 */
public class Deadline extends Task {

    protected final String by;
    protected LocalDate date;
    private boolean isDate = false;

    /**
     * Constructor that takes in a description and a deadline to do by.
     * Parses the deadline if it is given in a valid date format and stores as LocalDate.
     *
     * @param description String input.
     * @param by String input that can be turned into a date.
     */
    public Deadline(String description, String by) {
        super(description);
        this.date = parseDate(by);
        if (this.date != null) {
            this.isDate = true;
        }
        this.by = by;
    }

    private static LocalDate parseDate(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        try {
            return LocalDate.parse(dateString, formatter);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    @Override
    public String toString() {
        if (isDate) {
            assert date != null : "Date should not be null";
            return "[D]" + super.toString() + " (by: "
                    + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        }
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String toTxt() {
        return "D " + super.toTxt() + " | " + by;
    }
}
