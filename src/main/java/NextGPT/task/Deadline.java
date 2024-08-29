package NextGPT.task;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class for tasks with type deadline.
 */
public class Deadline extends Task {
    protected LocalDate by;

    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by);
    }

    public LocalDate getBy() {return this.by;}

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }

}
