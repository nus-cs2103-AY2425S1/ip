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
     * @param by          the due date and time of the task
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
        return this.getTypeIcon() + super.toString() + " (by: " + this.getDate() + ")";
    }

    private String get12HourFormat(LocalDateTime by) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
        return by.format(formatter);
    }

    private String getDate() {
        int day = this.by.getDayOfMonth();
        Month month = this.by.getMonth();
        int year = this.by.getYear();
        String time12HourFormat = get12HourFormat(this.by);
        String date = day + " " + month + " " + year + " " + time12HourFormat;
        return date;
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
