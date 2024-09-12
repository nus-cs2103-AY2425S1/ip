package revir.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline.
 * Inherits from the Task class.
 */
public class Deadline extends Task {
    private LocalDateTime deadline;

    /**
     * Creates a new Deadline object with the given description and deadline.
     *
     * @param description the description of the deadline
     * @param by          the deadline date and time
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.deadline = by;
    }

    /**
     * Returns a string representation of the Deadline object.
     *
     * @return A string representation of the Deadline object in the format: [D]
     *         &lt;description&gt; (by: &lt;deadline&gt;)
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline.format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm"))
                + ")";
    }

    public static String format() {
        return "deadline <description> /by <date>";
    }

}
