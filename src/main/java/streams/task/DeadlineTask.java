package streams.task;

import java.time.LocalDateTime;

public class DeadlineTask extends Task {
    private LocalDateTime by;

    public DeadlineTask(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    public LocalDateTime getBy() {
        return by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(Task.OUTPUT_FORMATTER) + ")";
    }
}