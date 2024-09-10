package assistinator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents deadline task
 */
public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Initialise deadline task
     * @param description task description
     * @param by deadline
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by);
    }

    /**
     * Convert task to formatted string
     * @return formatted string
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * Generate string to print onto file
     * @return String for file
     */
    public String toFileString() {
        return String.format("D | %d | %s | %s", isDone ? 1 : 0, description, by);
    }
}
