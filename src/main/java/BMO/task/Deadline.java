package bmo.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {

    private LocalDate by;

    /**
     * Constructor for Deadline class.
     *
     * @param description Description of the task
     * @param by Deadline date of the task
     */
    public Deadline(String description, String by) {
        super(description);

        this.by = super.getLocalDateFromString(by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.getPrintedDeadline() + ")";
    }

    /**
     * Returns the deadline in the format MMM-dd-yyyy to be printed in the console.
     */
    public String getPrintedDeadline() {
        return super.getPrintedDateFromLocalDate(by);
    }

    /**
     * Returns the deadline in the format dd/MM/yyyy to be stored in the file.
     */
    private String getStorageDeadline() {
        return this.by.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    @Override
    public String getSavedFormat() {
        return "D | " + (this.getIsDone() ? "1" : "0") + " | " + this.getDescription() + " | "
            + this.getStorageDeadline() + "\n";
    }
}
