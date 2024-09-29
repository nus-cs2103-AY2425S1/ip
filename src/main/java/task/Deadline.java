package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A deadline task.
 *
 * @author dwsc37
 */
public class Deadline extends Task {
    private final LocalDate deadline;

    /**
     * Constructor for a <code>Deadline</code>.
     *
     * @param description Description of the deadline.
     * @param deadline End date of the deadline
     */
    public Deadline(String description, LocalDate deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Constructor for a <code>Deadline</code>, with <code>isDone</code> included.
     *
     * @param description Description of the deadline.
     * @param isDone Completion status of the deadline.
     * @param deadline End date of the deadline.
     */
    public Deadline(String description, boolean isDone, LocalDate deadline) {
        super(description, isDone);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + ")";
    }

    @Override
    public String toData() {
        return "D | " + super.toData() + " | " + deadline;
    }

    @Override
    protected boolean isOnDate(LocalDate date) {
        return deadline.equals(date);
    }
}

