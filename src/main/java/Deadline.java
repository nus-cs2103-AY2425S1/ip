import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime by;
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy, hh:mm a");

    public Deadline(String description, String by) {
        super(description, TaskType.DEADLINE);
        this.by = LocalDateTime.parse(by, INPUT_FORMAT);
    }

    public LocalDateTime getBy() {
        return by;
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s (by: %s)", type.getSymbol(), status.getSymbol(), description, by.format(OUTPUT_FORMAT));
    }
}