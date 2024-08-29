package michaelscott.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private final LocalDateTime deadlineDate;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

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
        return "[D]" + (isDone ? "[X] " : "[ ] ") + desc + " (by: " + this.deadlineDate.format(formatter) + ")";
    }

    @Override
    public String toFile() {
        return "D | " + (isDone ? "1" : "0") + " | " + desc + " | " + this.deadlineDate.format(formatter);
    }
}
