import java.time.*;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate by;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}

