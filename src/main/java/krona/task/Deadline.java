package krona.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a deadline that the user
 * can input date and time.
 */
public class Deadline extends Task {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    protected LocalDateTime dateTime;

    /**
     * Contructs a new Deadline task.
     *
     * @param description The description of the task.
     * @param dateTime The due date of the task.
     */
    public Deadline(String description, String dateTime) {
        super(description);
        try {
            this.dateTime = LocalDateTime.parse(dateTime.trim(), FORMATTER);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please use 'd/M/yyyy HHmm'.");
            throw e;
        }
    }

    public String getDateTime() {
        return dateTime.format(FORMATTER);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a")) + ")";
    }
}
