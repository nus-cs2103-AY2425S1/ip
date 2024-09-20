package bobby.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task.
 * A Deadline is a type of task that needs to be completed by a specific date.
 */
public class Deadline extends Task {

    private LocalDate by;

    /**
     * Constructs a new Deadline task with the given description and due date.
     *
     * @param description The description of the Deadline task.
     * @param by          The date by which the task needs to be completed.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a string representation of the Deadline task.
     * The format includes the type of task, the output of the Task's toString method,
     * and the due date formatted as "MMM dd yyyy".
     *
     * @return A string in the format "[D][statusIcon] description (by: due_date)".
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return "[D]" + super.toString() + " (by: " + by.format(formatter) + ")";
    }

    /**
     * Returns a string representation of the Deadline task formatted for saving to a file.
     * The format is "D | isDone | description | due_date".
     *
     * @return A string formatted for file storage.
     */
    @Override
    public String toFileString() {
        return "D" + " " + super.toFileString() + " | " + this.by;
    }

    /**
     * Checks if the Deadline is due on the given date.
     *
     * @param date The date to check.
     * @return True if the task is due on the given date; otherwise, false.
     */
    @Override
    public boolean isOnDate(LocalDate date) {
        return by.equals(date);
    }
}
