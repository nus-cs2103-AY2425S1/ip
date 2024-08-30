package bmo.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    private LocalDate from;
    private LocalDate to;
    private DateTimeFormatter outputFormatter;

    public Event(String description, String from, String to) {
        super(description);

        // Define input date format
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Convert string to  LocalDate objects
        this.from = LocalDate.parse(from, inputFormatter);
        this.to = LocalDate.parse(to, inputFormatter);

        // Define output date format
        DateTimeFormatter outputFormatter =  DateTimeFormatter.ofPattern("MMM-dd-yyyy");
        this.outputFormatter = outputFormatter;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.getPrintedFrom() + " to: " + this.getPrintedTo() + ")";
    }
    
    /**
     * Returns the start date in the format MMM-dd-yyyy to be printed in the console.
     */
    private String getPrintedFrom() {
        return this.from.format(this.outputFormatter);
    }

    /**
     * Returns the end date in the format MMM-dd-yyyy to be printed in the console.
     */
    private String getPrintedTo() {
        return this.to.format(this.outputFormatter);
    }

    /**
     * Returns the start date in the format dd/MM/yyyy to be stored in the file.
     */
    public String getStorageFrom() {
        return this.from.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    /**
     * Returns the end date in the format dd/MM/yyyy to be stored in the file.
     */
    public String getStorageTo() {
        return this.to.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    @Override
    public String getSavedFormat() {
        return "E | " + (this.getIsDone() ? "1" : "0") + " | " + this.getDescription() + " | " + this.getStorageFrom() + " | " + this.getStorageTo() + "\n";
    }
}
