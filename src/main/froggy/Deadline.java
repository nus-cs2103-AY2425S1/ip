package main.froggy;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    protected String by;
    protected LocalDate date;
    private boolean isDate = false;

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
            LocalDate date = LocalDate.parse(dateString, formatter);
            return date;
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    @Override
    public String toString() {
        if (isDate) {
            return "[D]" + super.toString() + " (by: " +
                    date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        }
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String toTxt() {
        return "D " + super.toTxt() + " | " + by;
    }
}
