package strand.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import strand.exception.StrandException;

/**
 * The strand.task.Deadline class represents a task with a specific deadline.
 */
public class Deadline extends Task {
    protected LocalDateTime deadline;
    protected String stringDeadline;

    /**
     * Constructs a new strand.Tasks.Deadline task with the specified description and deadline.
     *
     * @param description The description of the task.
     * @param date        The deadline by which the task needs to be completed.
     * @throws StrandException Throws an exception if the description is empty
     */
    public Deadline(String description, String date) throws StrandException {
        super(description);
        try {
            this.deadline = this.parseDate(date);
        } catch (DateTimeParseException e) {
            this.stringDeadline = date;
        }
    }

    @Override
    String getType() {
        return "[D]";
    }

    /**
     * Returns the string representation of the strand.Tasks.Deadline task
     *
     * @return A string representing the strand.Tasks.Deadline task
     */
    @Override
    public String toString() {
        String deadline = this.deadline != null ? this.parseOutputDate(this.deadline) : this.stringDeadline;
        return String.format("%s%s (by: %s)",
                this.getType(),
                super.toString(),
                deadline);
    }

    @Override
    public String convertToFileFormat() {
        return String.format("D | %s | %s", super.convertToFileFormat(),
                this.deadline != null ? this.deadline : this.stringDeadline);
    }
}
