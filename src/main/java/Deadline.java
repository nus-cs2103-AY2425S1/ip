import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Provides the skeleton for a kind of Task named Deadline.
 */
public class Deadline extends Task{
    private final LocalDateTime by;
    public Deadline(String name, LocalDateTime by) {
        super(name.trim());
        this.by = by;
    }

    @Override
    public String taskData() {
        return String.format("D" + super.taskData() + deli + by.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm")) + "\n");
    }

    @Override
    public String toString() {
        return String.format("[D]" + super.toString()
            + " (by: " + by.format(DateTimeFormatter.ofPattern("yyyy MMM dd  HH:mm")) + ")");
    }
}
