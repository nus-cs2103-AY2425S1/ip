import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Deadline extends Task {
    private final LocalDate by;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + (isDone ? "[X] " : "[ ] ") + desc + " (by: " + this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String toFile() {
        return "D | " + (isDone ? "1" : "0") + " | " + desc + " | " + this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
}
