package yapper.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import yapper.exception.YapperException;

/**
 * Represents a task with a deadline. In addition to the task description,
 * this class stores the date and time by which the task should be completed.
 */
public class Deadline extends Task {
    private LocalDateTime by;

    /**
     * Constructs a Deadline task with the specified description and deadline.
     *
     * @param description The description of the task.
     * @param by          The deadline of the task in the format yyyy-MM-dd HHmm.
     * @throws YapperException If the date and time format is incorrect.
     */
    public Deadline(String description, String by) throws YapperException {
        super(description);
        try {
            this.by = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        } catch (DateTimeParseException e) {
            throw new YapperException("Boss, the date and time format seems off! Please use yyyy-MM-dd HHmm.");
        }
    }

    /**
     * Returns the string representation of the task for saving to a file.
     *
     * @return A string in the format "D | status | description | deadline".
     */
    @Override
    public String toSaveFormat() {
        return "D | " + (isDone() ? "1" : "0") + " | " + getDescription() + " | "
            + by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    /**
     * Returns the string representation of the task for display.
     *
     * @return A string in the format "[D][status] description (by: deadline)".
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mma")) + ")";
    }

    /**
     * Snoozes the deadline by a specified number of days or hours.
     *
     * @param snoozeAmount The amount to snooze by, e.g., "2d" for 2 days or "3h" for 3 hours.
     * @throws YapperException If the snooze format is incorrect.
     */
    public void snoozeDeadline(String snoozeAmount) throws YapperException {
        if (snoozeAmount.endsWith("d")) {
            int daysToSnooze = Integer.parseInt(snoozeAmount.replace("d", ""));
            this.by = this.by.plusDays(daysToSnooze);
        } else if (snoozeAmount.endsWith("h")) {
            int hoursToSnooze = Integer.parseInt(snoozeAmount.replace("h", ""));
            this.by = this.by.plusHours(hoursToSnooze);
        } else {
            throw new YapperException("Invalid snooze format. Use 'd' for days or 'h' for hours. "
                + "Example: snooze 1 2d (for 2 days) or snooze 2 3h (for 3 hours).");
        }
    }

}
