import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {
    private LocalDate endTime;

    public Deadline(String description, LocalDate endTime) {
        super(description);
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + String.format(" (by: %s)", endTime.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}
