package handsome.task;

import java.time.DateTimeException;

import handsome.exception.InvalidTaskException;


/**
 * The Deadline class represents a task with a deadline in the Handsome
 * chatBot application. It extends the Task class and adds a specific
 * deadline date and time to the task description.
 */
public class Deadline extends Task {
    private final String deadline;

    /**
     * Constructs a Deadline object with the specified content, status, and deadline.
     * If the isFormatted parameter is false, the deadline date and time are formatted
     * using the formatDate method; otherwise, the provided deadline string is used as-is.
     *
     * @param content     The description of the deadline task.
     * @param isDone      The status of the task, either marked as done or not done.
     * @param deadline    The deadline date and time as a string.
     * @param isFormatted A boolean indicating whether the deadline is already formatted.
     * @throws InvalidTaskException If the deadline date and time are incorrectly formatted.
     */
    public Deadline(String content, String isDone, String deadline, boolean isFormatted) throws InvalidTaskException {
        super(content, isDone);
        if (isFormatted) {
            this.deadline = deadline;
        } else {
            try {
                this.deadline = formatDate(deadline);
            } catch (DateTimeException error) {
                throw new InvalidTaskException("Date and time must be formatted correctly for deadline! "
                        + "Type prompt to check the correct date format");
            }
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + deadline + ")";
    }

    @Override
    public String toDataFormat() {
        return "D | " + super.toDataFormat() + " | " + deadline;
    }
}
