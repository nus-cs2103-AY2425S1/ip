package ratchet.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline task.
 */
public class DeadlineTask extends Task {
    private final LocalDate deadline;

    /**
     * Constructor for new Deadline task.
     *
     * @param description The description of the task.
     * @param deadline    The deadline of the task.
     */
    public DeadlineTask(String description, LocalDate deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Constructor for loading Deadline task.
     *
     * @param description The description of the task.
     * @param isDone      The status of the task.
     * @param deadline    The deadline of the task.
     */
    public DeadlineTask(String description, boolean isDone, LocalDate deadline) {
        super(description, isDone);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String toSave() {
        return "D|" + super.toSave() + "|" + deadline;
    }
}
