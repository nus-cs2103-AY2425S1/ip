package waterfall.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a specific <code>Task</code> type known as <code>Deadline</code>.
 * The <code>Deadline</code> object consists of a title and a deadline.
 * It consists of methods for formatting and displaying the deadline.
 *
 * @author Wai Hong
 */

public class Deadline extends Task {
    private final LocalDateTime deadline;

    /**
     * Constructs a Deadline task with the specified title and deadline.
     *
     * @param title The title of the task.
     * @param deadline The deadline of the task.
     */
    public Deadline(String title, String deadline) {
        super(title);
        this.deadline = parseDateTime(deadline);
    }

    /**
     * Parses a datetime string into a LocalDateTime object.
     *
     * @param dateString Datetime string in the format "yyyy-MM-dd HHmm".
     * @return A LocalDateTime object representing the parsed date time.
     */
    private LocalDateTime parseDateTime(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return LocalDateTime.parse(dateString, formatter);
    }

    /**
     * Format the deadline into the storage format.
     *
     * @return A deadline string in the storage format.
     */
    private String getFormattedDeadline() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return deadline.format(formatter);
    }

    /**
     * Format the deadline into displayable string.
     *
     * @return deadline string to be displayed.
     */
    private String getBeautifulDeadline() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy HH:mm");
        return deadline.format(formatter);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.getBeautifulDeadline() + ")";
    }

    @Override
    public String toStorageString() {
        if (this.isDone()) {
            return "D | 1 | " + this.getTitle() + " | " + this.getFormattedDeadline();
        } else {
            return "D | 0 | " + this.getTitle() + " | " + this.getFormattedDeadline();
        }
    }
}
