import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime by;
    private DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM d yyyy HH:mm");

    public Deadline(String description, String by) {
        super(description);
        this.by = Parser.parseDateTime(by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(outputFormatter) + ")";
    }

    @Override
    public String toFileString() {
        return String.format("D | %d | %s | %s", super.status() ? 1 : 0, getDescription(),
                by.format(Parser.getFormatters().get(0)));
    }
}
