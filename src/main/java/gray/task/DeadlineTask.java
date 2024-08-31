package gray.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A task that has a deadline.
 */
public class DeadlineTask extends Task {
    private final LocalDateTime deadline;

    /**
     * Constructs a task with deadline.
     * @param description
     * @param deadline
     */
    public DeadlineTask(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
        return String.format("[D]%s (by: %s)",
                super.toString(),
                deadline.format(formatter));
    }
}
