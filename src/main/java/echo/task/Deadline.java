package echo.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with deadlines
 *
 * @author Ernest Lim
 */
public class Deadline extends Task {
    private LocalDateTime deadline;

    /**
     * Constructor for Deadlines object
     *
     * @param deadlineArray array of string with the format [description, deadline]
     * @throws DateTimeParseException if the format of the deadline is not the same as INPUT_FORMATTER
     */
    public Deadline(String[] deadlineArray) throws DateTimeParseException {
        super(deadlineArray);
        this.deadline = LocalDateTime.parse(deadlineArray[1], INPUT_FORMATTER);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + deadline.format(OUTPUT_FORMATTER) + ")";
    }

    /**
     * Returns the Deadline as a fancier string e.g. Deadline | 0 | book | /by 12-12-1122 1211
     * Meant for recording in text files
     *
     * @return Fancier string of the Deadline
     */
    @Override
    public String toFancyString() {
        return "Deadline | " + super.getStatus() + " | " + super.description
                + " | /by " + super.reverseLocalDateTimeParse(deadline);
    }
}
