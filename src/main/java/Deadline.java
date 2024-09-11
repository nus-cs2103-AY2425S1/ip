import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that has a deadline.
 * @author Aaron
 */
public class Deadline extends Task {
    protected String dueBy;
    protected LocalDate dueDate;
    protected String dueByTime;

    public Deadline(String description, boolean isDone, String dueBy) {
        super(description, isDone);
        this.dueBy = dueBy;
        dueDate = LocalDate.parse(this.dueBy.substring(0, 10)); // First 10 characters of this.by represent "yyyy-mm-dd"
        dueByTime = this.dueBy.substring(this.dueBy.length() - 5); // Last 4 characters represent time in "hh:mm"
    }

    public String getDueBy() {
        return dueBy;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dueDate.format(DateTimeFormatter.ofPattern("MMM d, yyyy")) + " " +
                dueByTime + ")";
    }
}
