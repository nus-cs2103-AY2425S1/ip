import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime deadline;

    public Deadline(String description, String by) {
        super(description, TaskType.DEADLINE);
        this.deadline = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + deadline.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")) + ")";
    }

    @Override
    public String toFileString() {
        return "[D] | " + getStatusIcon() + " | " + getDescription() + " | " + deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy h:mma"));
    }
}
