package papadom.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
 * Represents a deadline task.
 * A deadline task contains a description and a deadline date, with or without a specific time.
 */
public class Deadline extends Task {
    protected LocalDateTime localDateTime;
    protected LocalDate localDate;
    protected boolean hasTime;
    /**
     * Constructs a Deadline task with a description and a specific deadline date and time.
     *
     * @param description The description of the deadline task.
     * @param by The deadline date and time for the task.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.localDateTime = by;
        this.hasTime = true;
    }
    /**
     * Constructs a Deadline task with a description and a deadline date without time.
     *
     * @param description The description of the deadline task.
     * @param by The deadline date for the task.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.localDate = by;
        this.hasTime = false;
    }
    /**
     * Returns a string representation of the deadline task, including the deadline date and time if available.
     *
     * @return A string representing the deadline task.
     */
    @Override
    public String toString() {
        if (hasTime) {
            return "[D]" + super.toString() + " (by: " + localDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy, hh:mm a")) + ")";
        } else {
            return "[D]" + super.toString() + " (by: " + localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        }
    }
}
