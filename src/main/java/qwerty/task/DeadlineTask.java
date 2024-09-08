package qwerty.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

/**
 * This class encapsulates a Deadline type task.
 * A Deadline contains a time by which it is due.
 */
public abstract class DeadlineTask extends Task {

    /** The deadline of the task */
    private final LocalDateTime deadline;

    /**
     * Creates a new Deadline instance.
     *
     * @param description String description of the deadline.
     * @param deadline Due date of the deadline.
     */
    public DeadlineTask(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }

    public LocalDateTime getDeadline() {
        return this.deadline;
    }

    /**
     * Returns the deadline as a formatted string.
     * The format is "MMM dd yyyy HHmm", e.g. "Aug 26 2024 1450".
     *
     * @return Formatted string representing the deadline.
     */
    public String getDeadlineAsString() {
        return this.deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"));
    }
}
