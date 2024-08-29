import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate by;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    public String formatString() {
        return String.format("D | %s | %s", super.formatString(), this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }

    @Override
    public String toString() {
        return String.format("[D] %s (by: %s)", super.toString(), this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}
