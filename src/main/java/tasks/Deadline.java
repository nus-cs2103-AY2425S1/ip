package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    protected LocalDate by;

    public Deadline(String description, String by) throws DeadlineException {
        super(description);

        // Parse datetime
        try {
            this.by = LocalDate.parse(by);

        } catch (DateTimeParseException e) {
            throw new DeadlineException("Cannot parse due date");
        }
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
