package Tasks;

import enums.TaskType;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate deadline;  // Using LocalDate instead of LocalDateTime

    public Deadline(String description, String deadline) {
        super(description, TaskType.DEADLINE);
        this.deadline = LocalDate.parse(deadline, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public String getDeadline() {
        return deadline.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    @Override
    public String toFileFormat() {
        return "D" + super.toFileFormat() + " | " + deadline.format(DateTimeFormatter.ISO_LOCAL_DATE);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
