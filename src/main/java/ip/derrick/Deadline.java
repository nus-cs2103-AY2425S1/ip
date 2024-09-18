package ip.derrick;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Deadline containing a deadline that is to be adhered to.
 */
public class Deadline extends Task {

    private LocalDate newBy;
    private String by;

    /**
     * Initialises the class with a description and deadline.
     * @param description Description of the task
     * @param by Deadline of the task
     */
    public Deadline(String description, String by) {
        super(description);
        this.newBy = convertDate(by);
        this.by = by;
    }

    @Override
    public String toString() {
        String date = (newBy != null) ? newBy.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) : by;
        return "[D]" + super.toString() + "(by: " + date + ")";
    }

    @Override
    public String changeFormat() {
        return "D | " + (this.getStatusIcon().equals("X") ? "1" : "0") + " | " + this.description + " | " + this.by;
    }

    @Override
    public LocalDate convertDate(String date) {
        try {
            return LocalDate.parse(date.trim(), DateTimeFormatter.ISO_DATE);
        } catch (DateTimeParseException e) {
            return null;
        }
    }
}