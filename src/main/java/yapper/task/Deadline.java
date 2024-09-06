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
    private final LocalDateTime by;

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
}
