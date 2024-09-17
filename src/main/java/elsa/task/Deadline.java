package elsa.task;

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

    /**
     * Creates a Deadline task.
     *
     * @param description A description of the task.
     * @param isDone A boolean indicating whether the task is completed.
     * @param dueBy The deadline for the task, in the format "yyyy-mm-dd hh:mm".
     */
    public Deadline(String description, boolean isDone, String dueBy) {
        super(description, isDone);
        this.dueBy = dueBy;

        // First 10 characters of this.by represent "yyyy-mm-dd"
        dueDate = LocalDate.parse(this.dueBy.substring(0, 10));

        // Last 4 characters represent time in "hh:mm"
        dueByTime = this.dueBy.substring(this.dueBy.length() - 5);
    }

    public String getDueBy() {
        return dueBy;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dueDate.format(DateTimeFormatter.ofPattern("MMM d, yyyy")) + " "
                + dueByTime + ")";
    }
}
