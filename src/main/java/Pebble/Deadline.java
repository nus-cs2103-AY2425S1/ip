package Pebble;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate by;
    protected String stringBy;

    public Deadline(String description, String by) {
        super(description);
        try {
            this.by = LocalDate.parse(by);
        } catch (DateTimeException e) {
            this.stringBy = by;
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