import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    private final LocalDateTime by;
    public Deadline(String name, LocalDateTime by) {
        super(name.trim());
        this.by = by;
    }

    @Override
    public String taskData() {
        return String.format("D" + super.taskData() + deli + by + "\n");
    }

    @Override
    public String toString() {
        return String.format("[D]" + super.toString()
            + " (by: " + by.format(DateTimeFormatter.ofPattern("yyyy MMM dd  HH:mm")) + ")");
    }
}
