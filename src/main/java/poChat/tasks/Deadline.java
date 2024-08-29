package poChat;

import java.time.LocalDateTime;

public class Deadline extends Task {
    private final LocalDateTime deadline;

    public Deadline(String taskDescription, String deadline, boolean isDone) {
        super(taskDescription, isDone);
        this.deadline = toLocalDateTime(deadline);
    }

    public Deadline(String taskDescription, String deadline) {
        this(taskDescription, deadline, false);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + formatYearMonthDay(this.deadline) + ")";
    }
}
