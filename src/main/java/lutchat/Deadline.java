package lutchat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a deadline.
 * A Deadline is a task that needs to be completed by a specific date.
 */
public class Deadline extends Task {
    private LocalDate by;

    /**
     * Constructs a Deadline object.
     *
     * @param description The description of the task.
     * @param by          The date by which the task needs to be completed, in the format "yyyy-MM-dd".
     * @throws IllegalArgumentException If the date format is invalid.
     */
    public Deadline(String description, String by) {
        super(description);
        try {
            this.by = LocalDate.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format. Please use 'yyyy-MM-dd'.");
        }
    }

    /**
     * Returns the task type.
     *
     * @return A string representing the task type, which is "D" for Deadline.
     */
    @Override
    public String getTaskType() {
        return "D";
    }

    /**
     * Returns additional details for file formatting.
     *
     * @return A string containing the date in the format "yyyy-MM-dd".
     */
    @Override
    public String additionalDescDetailsToFileFormat() {
        return " | " + by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    /**
     * Returns the string representation of the Deadline task.
     *
     * @return A string representing the Deadline, including its description and due date.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
