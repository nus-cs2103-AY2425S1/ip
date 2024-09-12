package donna.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import donna.DonnaException;

/**
 * Represents a Deadline task which includes a description and a due date/time.
 */
public class Deadline extends Task {
    private final LocalDateTime byTime;
    private final String description;
    private final DateTimeFormatter inputFormatter =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    private final DateTimeFormatter outputFormatter =
            DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma");

    /**
     * Constructs a Deadline task with the specified description and due date/time.
     *
     * @param description The description of the task.
     * @param byTime The due date and time of the task in the format "dd/MM/yyyy HHmm".
     * @throws DonnaException If the description is empty or the date/time format is invalid.
     */
    public Deadline(String description, String byTime) throws DonnaException {
        super(description);

        assert byTime != null : "byTime should not be null";

        if (byTime.trim().isEmpty()) {
            throw DonnaException.emptyDeadline();
        }
        try {
            this.byTime = LocalDateTime.parse(byTime, inputFormatter);
        } catch (DateTimeParseException e) {
            throw new DonnaException(
                    "Invalid date and time format! Please use dd/MM/yyyy HHmm (24hr format)");
        }
        this.description = description;
    }

    /**
     * Returns a string representation of the deadline in a format suitable for saving to a file.
     *
     * @return A string representing the deadline in the format "D | mark status | description | date".
     */
    @Override
    public String toFileFormat() {
        return "D | " + (this.isDone() ? "1" : "0") + " | " + this.description + " | "
                + this.byTime.format(inputFormatter);
    }

    @Override
    public String getType() {
        return "D";
    }

    /**
     * Returns a string representation of the deadline task.
     * Includes the description, status, and due date/time.
     *
     * @return A string representing the deadline task.
     */
    @Override
    public String toString() {
        return super.toString()
                + "(by: " + this.byTime.format(outputFormatter) + ")";
    }
}
