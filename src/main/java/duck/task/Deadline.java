package duck.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task with a description and a due date.
 */
public class Deadline extends Task {

    protected LocalDate by;

    /**
     * Constructs a duke.task.Deadline task with description and due date.
     *
     * @param description The description of the deadline.
     * @param by The due date of the deadline.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a string representation of this deadline task.
     *
     * @return A string representation of this deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    public String toFileString() {
        return "D | " + (this.isDone ? "1" : "0") + " | " + this.description + " | " + this.by;
    }
}