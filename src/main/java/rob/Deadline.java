package rob;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Formats a date string from "yyyy-MM-dd" to "d MMM yyyy".
     * If invalid format, returns original string.
     *
     * @param by The date string in "yyyy-MM-dd" format.
     * @return The formatted date string in "d MMM yyyy" format, or the original string if parsing fails.
     */
    private String formatDate(String by) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            LocalDate date = LocalDate.parse(by, formatter);
            return date.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
        } catch (DateTimeParseException e) {
            // System.out.println("Invalid date format: " + e.getMessage());
            return by;
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + formatDate(by) + ")";
    }

    @Override
    public String toSaveString() {
        return "[D] | " + getStatusIcon() + " | " + description + " | " + by;
    }
}