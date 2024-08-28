package michaelscott.task;

import java.time.LocalDateTime;

public class Deadline extends Task {
    private final LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    public LocalDateTime getDeadlineDate() {
        return by;
    }

    public String getDescription() {
        return desc;
    }

    @Override
    public String toString() {
        return "[D]" + (isDone ? "[X] " : "[ ] ") + desc + " (by: " + this.by + ")";
    }

    @Override
    public String toFile() {
        return "D | " + (isDone ? "1" : "0") + " | " + desc + " | " + this.by + ")";
    }
}
