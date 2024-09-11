package deadline;

import task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task with a specific due date.
 */
public class Deadline extends Task {
    protected LocalDate deadline;
    /**
     * Constructs a Deadline task with the specified description, deadline, and input.
     *
     * @param description The description of the deadline task.
     * @param deadline The due date of the deadline task.
     * @param input The input used to create the deadline task.
     */
    public Deadline (String description, LocalDate deadline, String input) {
        super(description, input);
        this.deadline = deadline;
    }

    /**
     * Returns a string representation of the deadline task.
     *
     * @return The string representation of the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
