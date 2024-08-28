package alex;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime deadline;
    public Deadline (String taskName, boolean taskCompletionStatus, LocalDateTime deadline) {
        super(taskName, taskCompletionStatus);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy h.mma")) + ")";
    }

    @Override
    public String storageString() {
        return "[D]" + super.toString() + " /by " + this.deadline.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }
}
