package bmo.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task.
 */
public class Event extends Task {

    private LocalDate from;
    private LocalDate to;

    /**
     * Constructor for Event class.
     *
     * @param description Description of the task
     *
     * @param from Start date of the event
     * @param to End date of the event
     */
    public Event(String description, String from, String to) {
        super(description);

        // Convert string to LocalDate objects
        this.from = super.getLocalDateFromString(from);
        this.to = super.getLocalDateFromString(to);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.getPrintedStartDate() + " to: " + this.getPrintedEndDate() + ")";
    }

    /**
     * Returns the start date in the format MMM-dd-yyyy to be printed in the console.
     */
    private String getPrintedStartDate() {
        return super.getPrintedDateFromLocalDate(from);
    }

    /**
     * Returns the end date in the format MMM-dd-yyyy to be printed in the console.
     */
    private String getPrintedEndDate() {
        return super.getPrintedDateFromLocalDate(to);
    }

    /**
     * Returns the start date in the format dd/MM/yyyy to be stored in the file.
     */
    public String getStorageStartDate() {
        return this.from.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    /**
     * Returns the end date in the format dd/MM/yyyy to be stored in the file.
     */
    public String getStorageEndDate() {
        return this.to.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    @Override
    public String getSavedFormat() {
        return "E | " + (this.getIsDone() ? "1" : "0") + " | " + this.getDescription() + " | "
            + this.getStorageStartDate() + " | " + this.getStorageStartDate() + "\n";
    }
}
