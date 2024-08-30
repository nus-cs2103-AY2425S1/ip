package bill;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The Deadline class extends the Task class, and allows creating tasks with date deadlines.
 */
public class Deadline extends Task {

    private LocalDate by;

    /**
     * Initializes Deadline.
     *
     * @param description Description of deadline.
     * @param by Deadline of task in date format.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = parseUserDateHelper(by);
    }

    private LocalDate parseUserDateHelper(String dateStr) {
        // Allow two types of inputs, second format is what is from Bill.txt, user can use that format too for cml
        // Can improve this, ideally don't use try catch as control flow, could also try just allowing one input
        DateTimeFormatter formatterUserInput = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter formatterFileInput = DateTimeFormatter.ofPattern("MMM dd yyyy");
        try {
            return LocalDate.parse(dateStr, formatterUserInput);
        } catch (DateTimeParseException ex) {
            return LocalDate.parse(dateStr, formatterFileInput);
        }
    }

    private String dateStringRepresentation() {
        return by.format(DateTimeFormatter.ofPattern("MMM dd yyy"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dateStringRepresentation() + ")";
    }
}