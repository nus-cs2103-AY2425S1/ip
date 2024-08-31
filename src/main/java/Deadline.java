import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime by;
    private static final DateTimeFormatter OUTPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy, hh:mm a");;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    public LocalDateTime getBy() {
        return by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(OUTPUT_DATE_FORMAT) + ")";
    }
}