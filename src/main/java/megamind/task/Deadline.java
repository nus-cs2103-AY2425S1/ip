package megamind.task;

import java.io.Serial;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class Deadline extends Task{
    @Serial
    private static final long serialVersionUID = 1L;
    private final LocalDateTime deadline;

    /**
     * Constructor for the Deadline class.
     *
     * @param description Description of the task.
     * @param deadline Deadline of the task.
     * @exception DateTimeParseException If the deadline is not in the correct format.
     */
    public Deadline(String description, String deadline) throws DateTimeParseException {
        super(description);
        this.deadline = LocalDateTime.parse(deadline, INPUT_FORMATTER);
    }

    /**
     * Returns the String representation of the task.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (BY: " + deadline.format(OUTPUT_FORMATTER) + ")";
    }
}
