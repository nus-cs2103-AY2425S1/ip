import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime by;

    public Deadline(String description, LocalDateTime by, boolean isDone) {
        super(description, TaskType.DEADLINE, isDone);
        this.by = by; // store the deadline
    }

    @Override
    public String toFileFormat() {
        return "D | " + (getIsDone() ? "1" : "0") + " | " + getDescription() + " | "
                + by.format(DateTimeFormatter.ISO_DATE_TIME);
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + by.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a")) + ")";
    }
}
