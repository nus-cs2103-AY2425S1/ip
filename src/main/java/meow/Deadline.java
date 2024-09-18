package meow;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline.
 * A Deadline has a description and a due date.
 * This class extends the Task class and implements the Serializable interface.
 */
public class Deadline extends Task implements Serializable {

    protected LocalDate by;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the deadline of the task.
     *
     * @return The deadline as a LocalDate object.
     */
    public LocalDate getBy() {
        return by;
    }

    /**
     * Returns the string representation of the Deadline task, including its type,
     * description, and formatted deadline as "MMM dd yyyy".
     *
     * @return A string representing the Deadline task.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return "[D]" + super.toString() + " (by: " + by.format(formatter) + ")";
    }
}