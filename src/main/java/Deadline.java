import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private static final DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
    private final LocalDateTime by;

    public Deadline(String name, String by) {
        super(name);
        this.by = LocalDateTime.parse(by, inputFormatter);
    }

    public Deadline(boolean done, String name, String by) {
        super(done, name);
        this.by = LocalDateTime.parse(by, inputFormatter);
    }

    @Override
    public String toText() {
        return "D | " + super.toText() + " | " + by.format(inputFormatter);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + String.format(" (by: %s)", by.format(outputFormatter));
    }
}
