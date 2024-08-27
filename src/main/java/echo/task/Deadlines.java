package echo.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with deadlines
 *
 * @author Ernest Lim
 */
public class Deadlines extends Task {
    private LocalDateTime deadline;

    /**
     * Constructor for Deadlines object
     *
     * @param description description of the deadline task
     * @param deadline deadline of the task
     * @throws DateTimeParseException if the format of the deadline is not the same as INPUT_FORMATTER
     */
    public Deadlines(String description, String deadline) throws DateTimeParseException {
        super(description);
        this.deadline = LocalDateTime.parse(deadline, INPUT_FORMATTER);
    }

    /**
     * Returns the Deadline as a string with its status, description and deadline
     *
     * @return String of the Deadline
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + this.deadline.format(OUTPUT_FORMATTER) + ")";
    }

    /**
     * Returns the Deadline as a fancier string with its status, description and deadline
     * Meant for recording in text files
     *
     * @return Fancier string of the Deadline
     */
    @Override
    public String toFancyString() {
        return "Deadline | " + super.getStatus() + " | " + super.getDescription()
                + " | /by " + super.reverseLocalDateTimeParse(this.deadline);
    }
}
