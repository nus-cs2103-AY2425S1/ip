import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that has a deadline.
 * @author Aaron
 */
public class Deadline extends Task {
    protected String by;
    protected LocalDate dueDate;
    protected String dueByTime;

    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        this.by = by;
        dueDate = LocalDate.parse(this.by.substring(0, 10)); // First 10 characters of this.by represent "yyyy-mm-dd"
        dueByTime = this.by.substring(this.by.length() - 5); // Last 4 characters represent time in "hh:mm"
    }

    public String getBy() {
        return by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dueDate.format(DateTimeFormatter.ofPattern("MMM d, yyyy")) + " " +
                dueByTime + ")";
    }
}
