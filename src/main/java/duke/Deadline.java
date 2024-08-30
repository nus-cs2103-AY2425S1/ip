package duke;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task with a description and a due date and time.
 */
public class Deadline extends Task {

    protected LocalDateTime by;

    /**
     * Constructs a Deadline task with the specified description and due date and time.
     *
     * @param description the description of the task
     * @param by the due date and time of the task
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the type icon representing a deadline task.
     *
     * @return the type icon for a deadline task
     */
    public String getTypeIcon() {
        return "[D]";
    }

    /**
     * Returns a string representation of the deadline task,
     * including its type, status, description, and due date and time.
     *
     * @return a string representation of the deadline task
     */
    @Override
    public String toString() {
        int day = this.by.getDayOfMonth();
        Month month = this.by.getMonth();
        int year = this.by.getYear();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
        String time12HourFormat = this.by.format(formatter);
        String date = day + " " + month + " " + year + " " + time12HourFormat;
        return this.getTypeIcon() + super.toString() + " (by: " + date + ")";
    }

    /**
     * Returns the due date and time of the deadline task.
     *
     * @return the due date and time of the task
     */
    public LocalDateTime getBy() {
        return this.by;
    }
}
