package rose.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that starts at a specific date/time and ends at a specific date/time.
 *
 * <p>An <code>Event</code> object is represented by the description of the task, the starting date, the ending date,
 * the status of completion, and an optional tag. e.g., <code>[E][ ] attend career fair (from: Aug 01 2024
 * to: Aug 04 2024) #fun</code>.
 */
public class Event extends Task {
    protected LocalDate fromDate;
    protected LocalDate toDate;

    public Event(String taskName, LocalDate fromDate, LocalDate toDate) {
        super(taskName);
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public Event(String taskName, LocalDate fromDate, LocalDate toDate, String tag) {
        super(taskName, tag);
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public Event(String taskName, LocalDate fromDate, LocalDate toDate, boolean isDone, String tag) {
        super(taskName, isDone, tag);
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        String tagString = tag.isEmpty() ? "" : " #" + tag;
        return String.format("[E]%s (from: %s to: %s)%s",
                super.toString(), this.fromDate.format(formatter), this.toDate.format(formatter), tagString);
    }

    /**
     * Returns a string representation of the event task in a comma-separated format.
     *
     * <p>The format is: <code>"E,status,taskName,fromDate,toDate"</code>, where <code>status</code>
     * is "X" if the task is done, and a space (" ") if the task is not done. The <code>fromDate</code>
     * and <code>toDate</code> represent the start and end dates of the event, formatted as "yyyy-MM-dd".
     * The <code>tag</code> is optional and can be an empty string if the user doesn't give a tag.</p>
     *
     * @return A comma-separated string representing the event task's type, status, name, start date, and end date.
     */
    @Override
    public String commaString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return String.format("E,%s,%s,%s,%s",
                super.commaString(), this.fromDate.format(formatter), this.toDate.format(formatter), tag);
    }

    public LocalDate getDate() {
        return this.fromDate;
    }
}
