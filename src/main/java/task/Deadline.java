package task;

import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.Locale;

/**
 * A Task with a deadline.
 *
 * @author Toh Yi Hui A0259080A
 */
public class Deadline extends Task {
    private LocalDateTime deadline;

    /**
     * Constructor for a new Deadline task.
     *
     * @param description the description of the task.
     * @param deadline the deadline of the task.
     */
    public Deadline(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * constructor for a new Deadline with the specified status of the Deadline.
     *
     * @param description the description of the task.
     * @param isComplete the status of the task.
     * @param deadline the deadline of the task.
     */
    public Deadline(String description, boolean isComplete, LocalDateTime deadline) {
        super(description, isComplete);
        this.deadline = deadline;
    }

    /**
     * Return the dateline of the task.
     *
     * @return the dateline of the task.
     */
    public LocalDateTime getDeadline() {
        return deadline;
    }

    /**
     * Return the String representation of the task.
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), parseDeadlineToString());
    }

    /**
     * Return the String representation of the deadline.
     * Format example: 7 July Monday, 16:00H
     *
     * @return the formatted String.
     */
    private String parseDeadlineToString() {
        String dayOfMonth = String.valueOf(deadline.getDayOfMonth());
        String month = deadline.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH);
        String dayOfWeek = deadline.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        String hour = deadline.getHour() < 10
                ? "0" + String.valueOf(deadline.getHour())
                : String.valueOf(deadline.getHour());
        String minute = deadline.getMinute() < 10
                ? "0" + String.valueOf(deadline.getMinute())
                : String.valueOf(deadline.getMinute());
        return String.format("%s %s %s, %s:%sH", dayOfMonth, month, dayOfWeek, hour, minute);
    }
}
