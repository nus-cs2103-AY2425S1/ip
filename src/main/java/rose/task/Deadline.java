package rose.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that needs to be done before a specific date/time.
 * A <code>Deadline</code> object is represented by the description of the task, the date of deadline, and the status
 * of completion. e.g., <code>[D][X] return book (by: Aug 24 2024)</code>.
 */
public class Deadline extends Task {
    protected LocalDate byDate;

    public Deadline(String taskName, LocalDate byDate) {
        super(taskName);
        this.byDate = byDate;
    }

    public Deadline(String taskName, LocalDate byDate, String tag) {
        super(taskName, tag);
        this.byDate = byDate;
    }
    public Deadline(String taskName, LocalDate byDate, boolean isDone, String tag) {
        super(taskName, isDone, tag);
        this.byDate = byDate;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        String tagString = tag.isEmpty() ? "" : " #" + tag;
        return String.format("[D]%s (by: %s)%s",
                super.toString(), this.byDate.format(formatter), tagString);
    }

    /**
     * Returns a string representation of the deadline task in a comma-separated format.
     *
     * <p>The format is: <code>"D,status,taskName,byDate"</code>, where <code>status</code>
     * is "X" if the task is done, and a space (" ") if the task is not done. The <code>byDate</code>
     * is the deadline by which the task needs to be completed, formatted as "yyyy-MM-dd".</p>
     *
     * @return A comma-separated string representing the deadline task's type, status, name, and deadline.
     */
    @Override
    public String commaString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return String.format("D,%s,%s,%s",
                super.commaString(), this.byDate.format(formatter), tag);
    }

    public LocalDate getDate() {
        return this.byDate;
    }
}
