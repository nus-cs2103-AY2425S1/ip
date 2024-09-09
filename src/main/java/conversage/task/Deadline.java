package conversage.task;

import conversage.exception.ConverSageException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {

    protected static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    protected static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy, h:mma");

    protected LocalDateTime deadline;
    /**
     * Constructs a Deadline task with the specified description and deadline.
     *
     * @param taskDesc the description of the task.
     * @param deadline the deadline of the task.
     * @throws ConverSageException if the deadline format is invalid.
     */
    public Deadline(String taskDesc, String deadline) throws ConverSageException {
        super(taskDesc);

        try {
            this.deadline = LocalDateTime.parse(deadline, INPUT_FORMAT);
        } catch (DateTimeParseException e) {
            throw new ConverSageException("Invalid date/time format! Please use this format: yyyy-MM-dd HHmm");
        }

    }

    @Override
    public String toFileFormat() {
        return "conversage.task.Deadline | " + (isDone ? "Done" : "Not Done")
                + " | " + taskDesc + " | " + deadline.format(INPUT_FORMAT);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline.format(OUTPUT_FORMAT) + ")";
    }
}
