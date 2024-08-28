package michaelscott.task;

import java.time.LocalDateTime;

public class Deadline extends Task {
    private final LocalDateTime deadlineDate;

    public Deadline(String description, LocalDateTime deadlineDate) {
        super(description);
        this.deadlineDate = deadlineDate;
    }

    public LocalDateTime getDeadlineDate() {
        return deadlineDate;
    }

    public String getDescription() {
        return desc;
    }

    @Override
    public String toString() {
        return "[D]" + (isDone ? "[X] " : "[ ] ") + desc + " (by: " + this.deadlineDate + ")";
    }

    @Override
    public String toFile() {
        return "D | " + (isDone ? "1" : "0") + " | " + desc + " | " + this.deadlineDate + ")";
    }
}
