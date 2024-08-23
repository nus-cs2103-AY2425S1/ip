package citadel.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task in the Citadel application.
 * A deadline task has a description and a due date/time by which the task must be completed.
 * This class extends {@link Task}.
 */
public class Deadline extends Task {

    /** The date and time by which the task must be completed. */
    protected LocalDateTime dateTime;

    /**
     * Constructs a new {@code Deadline} task with the specified description and due date/time.
     *
     * @param description The description of the deadline task.
     * @param dateTime The date and time by which the task must be completed.
     */
    public Deadline(String description, LocalDateTime dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    /**
     * Returns a string representation of the deadline task, including its type, status icon,
     * description, and due date/time.
     *
     * @return A string representation of the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by: " + this.formatTime(this.dateTime) + ")";
    }

    /**
     * Returns a string representation of the deadline task for printing or saving,
     * including its type, status icon, description, and due date/time.
     *
     * @return A string representation of the deadline task for printing or saving.
     */
    public String printTask() {
        return "[D]" + super.toString() + " (by: "
                + this.dateTime
                .format(DateTimeFormatter.ofPattern("dd MM yyyy HH:mm"))
                + ")";
    }

    /**
     * Formats the given {@code LocalDateTime} object into a string using the pattern "dd/MM/yyyy HH:mm".
     *
     * @param time The date and time to be formatted.
     * @return A string representation of the formatted date and time.
     */
    private String formatTime(LocalDateTime time) {
        return time
                .format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }
}
