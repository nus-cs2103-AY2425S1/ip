package toothless.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import toothless.exceptions.ToothlessExceptions;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {

    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy[ ]HHmm");
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");
    protected LocalDateTime deadline;

    /**
     * Constructor for Deadline.
     *
     * @param description the description of the deadline
     * @param deadline    the deadline of the task
     * @throws ToothlessExceptions if the date and time format is invalid
     */
    public Deadline(String description, String deadline) throws ToothlessExceptions {
        super(description);
        try {
            this.deadline = LocalDateTime.parse(deadline.trim().replace("-", "/"), INPUT_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new ToothlessExceptions("Please enter a valid date and time\n"
                    + "in the format: dd/MM/yyyy HHmm or dd-MM-yyyy HHmm\n");
        }
    }

    /**
     * Constructor for Deadline.
     *
     * @param description the description of the deadline
     * @param deadline    the deadline of the task
     * @param isDone      the status of the deadline
     * @throws ToothlessExceptions if the date and time format is invalid
     */
    public Deadline(String description, String deadline, boolean isDone) throws ToothlessExceptions {
        super(description, isDone);
        try {
            this.deadline = LocalDateTime.parse(deadline.trim().replace("-", "/"), INPUT_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new ToothlessExceptions("Please enter a valid date and time\n"
                    + "in the format: dd/MM/yyyy HHmm or dd-MM-yyyy HHmm\n");
        }
    }

    @Override
    public String toFileString() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + deadline.format(INPUT_FORMATTER);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline.format(OUTPUT_FORMATTER) + ")";
    }
}
