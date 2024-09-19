package pixy.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a Task object with a specified due date and time.
 */
public class Deadlines extends Task {

    /** Due date of the given Task*/
    private LocalDateTime dueDateTime;

    /**
     * Creates a Deadline object with specified due date.
     *
     * @param description The description of the task.
     * @param by The due date and time of the task.
     */
    public Deadlines(String description, String by) {
        super(description);
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        DateTimeFormatter displayFormatter = DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a");
        try {
            this.dueDateTime = LocalDateTime.parse(by.trim(), inputFormatter);
        } catch (DateTimeParseException e) {
            try {
                this.dueDateTime = LocalDateTime.parse(by.trim(), displayFormatter);
            } catch (DateTimeParseException ex) {
                throw new IllegalArgumentException("Invalid date format: " + by +
                        ". Please use 'd/M/yyyy HHmm' or 'MMM d yyyy, h:mm a'.");
            }
        }
    }


    /**
     * Returns the due date and time of the task.
     *
     * @return A formatted string representation of the deadline.
     */
    public String getDueDateTime() {
        return dueDateTime
                .format(DateTimeFormatter
                        .ofPattern("MMM d yyyy, h:mm a"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by: " + getDueDateTime() + ")";
    }
}
