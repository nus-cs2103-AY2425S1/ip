import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

public class Deadline extends Task {
    protected LocalDate by;

    public Deadline(String taskName,
            LocalDate by) {
        super(taskName);
        this.by = by;
    }

    @Override
    public String toString() {
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return "[D]" + super.toString() + " (by: " + this.by.format(pattern) + ")";
    }
}
