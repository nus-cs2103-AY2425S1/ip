package assistinator.tasks;

import java.time.LocalDateTime;

import assistinator.TaskStatus;

/**
 * Represents deadline task.
 */
public class DeadlineTask extends Task {
    protected LocalDateTime by;

    /**
     * Initialises a deadline task with deadline as LocalDateTime.
     * @param description Task description.
     * @param by Deadline.
     */
    public DeadlineTask(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Initialises a deadline task with deadline as string.
     * @param description Task description.
     * @param by Deadline.
     */
    public DeadlineTask(String description, String by) {
        super(description);
        this.by = LocalDateTime.parse(by, formatter);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(formatter) + ")";
    }

    /**
     * {@inheritDoc}
     */
    public String toStorageString() {
        return String.format(
                "D | %s | %s | %s", isDone ? TaskStatus.DONE : TaskStatus.NOTDONE, description, by.format(formatter)
        );
    }

    public LocalDateTime getDeadline() {
        return by;
    }
}
