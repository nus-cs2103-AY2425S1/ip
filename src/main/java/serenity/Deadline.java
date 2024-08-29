package serenity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a type of Task that has a deadline.
 */
public class Deadline extends Task {

    protected LocalDate date;

    /**
     * Constructs a Deadline.
     *
     * @param description Description of task.
     * @param by Deadline of task.
     */
    public Deadline(String description, String by) {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("[dd/MM/yyyy]" + "[dd MMM yyyy]");
        this.date = LocalDate.parse(by, formatter);
    }

    /**
     * Returns string representation of Deadline.
     *
     * @return String representation.
     */
    @Override
    public String toString() {
        String formatDate = date.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
        return "[D]" + super.toString() + " (by: " + formatDate + ")";
    }

    /**
     * Returns string representation of Deadline to save as data.
     *
     * @return String representation to save as data.
     */
    @Override
    public String formatData() {
        String formatDate = date.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
        return "D | " + super.formatData() + " | " + formatDate;
    }
}
