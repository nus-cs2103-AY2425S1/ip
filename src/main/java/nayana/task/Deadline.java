package nayana.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a specific deadline.
 */
public class Deadline extends Task {

    private LocalDate date;

    /**
     * Constructs a deadline task with the given description and end time.
     *
     * @param description The description of the task.
     * @param date The end time of the deadline.
     */
    public Deadline(String description, LocalDate date) {
        super(description);
        this.date = date;
    }

    /**
     * Returns the type of the task as a string.
     *
     * @return A string "D " representing the deadline type.
     */
    @Override
    public String getType() {
        return "D ";
    }

    /**
     * Returns the deadline date as a string.
     *
     * @return A string representing the deadline date prefixed by "| ".
     */
    @Override
    public String getDates() {
        return "| " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Returns a string representation of the deadline task.
     *
     * @return A string in the format "[D] description (by: end)".
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    public LocalDate getDate() {
        return this.date;
    }

}
