package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDate by;

    public Deadline(String description, String by) {
        super(description);

        // Parse datetime
        this.by = LocalDate.parse(by);
    }

    /**
     * Returns a formatted string representation
     *
     * @return data format
     */
    @Override
    public String toDataFormat() {
        return "deadline | " + super.toDataFormat() + " | " + by;
    }

    /**
     * Returns a formatted message with status icon
     * and description
     *
     * @return display message
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " 
                + by.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + ")";
    }
}
