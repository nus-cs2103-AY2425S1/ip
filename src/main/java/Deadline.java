import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private final DateTimeFormatter OUT_FORMATTER = DateTimeFormatter.ofPattern("dd MMM yyyy");

    protected LocalDate by;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    // add a comment to test 
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(OUT_FORMATTER) + ")";
    }
}
