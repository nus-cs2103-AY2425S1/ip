package wenjiebot.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Deadline class represents a deadline task in the wenjiebot application.
 * It extends the Task class and includes additional information for the deadline,
 * specifically the due date and time.
 */
public class Deadline extends Task {

    protected String by;
    protected LocalDateTime dateTime;

    /**
     * Constructs a Deadline with the specified description and due date.
     * The due date is parsed into a LocalDateTime object using the format "d/M/yyyy HHmm".
     *
     * @param description The description of the deadline.
     * @param by The due date and time of the deadline in the format "d/M/yyyy HHmm".
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by.trim();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        this.dateTime = LocalDateTime.parse(by.trim(), formatter);
    }

    /**
     * Returns a string representation of the Deadline in a format suitable for storage.
     * The format includes the type of task, description, and due date and time.
     *
     * @return A string representation of the Deadline for storage.
     */
    @Override
    public String toPrettierString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        return "D" + super.toPrettierString() + "/by " + dateTime.format(formatter);
    }

    /**
     * Returns a string representation of the Deadline for display purposes.
     * The format includes the type of task, description, and due date and time,
     * formatted as "EEE, dd MMM yyyy HH:mm".
     *
     * @return A string representation of the Deadline for display.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm");
        return "[D]" + super.toString() + "(by: " + dateTime.format(formatter) + ")";
    }
}
