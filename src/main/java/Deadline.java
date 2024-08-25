import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("dd MMM yyyy HHmm");
        return "[D]" + super.toString() + "(by: " + by.format(outputFormat) + "HRS)";
    }

    @Override
    public String formatted() {
        DateTimeFormatter fileFormat = DateTimeFormatter.ofPattern("dd-MM-yy HHmm");
        return "D | " + super.formatted() + "| " + by.format(fileFormat);
    }
}
