package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task, which contains a description and a deadline (by when it should be completed).
 * A Deadline task can also be marked as done or not done.
 */
public class Deadline extends Task {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm");
    private LocalDateTime by;
    /**
     * Creates a new Deadline task.
     *
     * @param description Description of the task.
     * @param by Deadline in "yyyy-MM-dd HH:mm" format.
     */
    public Deadline(String description, String by) {
        super(description, TaskType.DEADLINE);
        this.by = LocalDateTime.parse(by, FORMATTER);
    }

    /**
     * Creates a new Deadline task with an option to mark it as done.
     *
     * @param description Description of the task.
     * @param by Deadline in "yyyy-MM-dd HH:mm" format.
     * @param isDone True if the task is already done.
     */
    public Deadline(String description, String by, boolean isDone) {
        super(description, TaskType.DEADLINE, isDone);
        this.by = LocalDateTime.parse(by, FORMATTER);
    }

    /**
     * Returns deadline of the task.
     *
     * @return Deadline as a LocalDateTime object.
     */
    public LocalDateTime getBy() {
        return by;
    }

    /**
     * Returns type of the task.
     *
     * @return Type of task.
     */
    @Override
    public String getType() {
        return "[D]";
    }

    /**
     * Snoozes the deadline by the specified number of days.
     *
     * This method adjusts the deadline date by adding the specified number of days
     * to the current deadline date.
     *
     * @param days The number of days to snooze the deadline by.
     */
    public void snooze(int days) {
        this.by = this.by.plusDays(days);
    }

    /**
     * Returns string representation of the deadline task.
     *
     * @return Task details.
     */
    @Override
    public String toString() {
        return getType() + getStatusIcon() + " " + description + " (by: " + by.format(OUTPUT_FORMATTER) + ")";
    }
}
