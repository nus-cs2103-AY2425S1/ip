package strand.task;

import java.time.LocalDateTime;

import strand.exception.StrandException;

/**
 * The strand.Tasks.Deadline class represents a task with a specific deadline.
 */
public class Deadline extends Task {
    protected LocalDateTime deadline;

    /**
     * Constructs a new strand.Tasks.Deadline task with the specified description and deadline.
     *
     * @param description The description of the task.
     * @param date        The deadline by which the task needs to be completed.
     * @throws StrandException Throws an exception if the description is empty
     */
    public Deadline(String description, String date) throws StrandException {
        super(description);
        this.deadline = this.parseDate(date);
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
        return String.format("%s%s (by: %s)",
                this.getType(),
                super.toString(),
                this.parseOutputDate(this.deadline));
    }

    @Override
    public String getFile() {
        return String.format("D | %s | %s", super.getFile(), this.deadline);
    }
}
