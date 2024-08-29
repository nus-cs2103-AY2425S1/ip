package data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Task with a deadline (endTime)
 */
public class Deadline extends Task {
    private LocalDateTime endTime;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /**
     * Constructor for Deadline
     *
     * @param desc description of task to be done
     * @param endTime date and time that task needs to be done by
     * @param isDone true if task is done; else false
     */
    public Deadline(String desc, LocalDateTime endTime, boolean isDone) {
        super(desc, isDone);
        this.endTime = endTime;
    }

    @Override
    public String getSaveTaskString() {
        return "D" + super.getSaveTaskString() + "|" + this.endTime.format(FORMATTER);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + this.endTime.format(FORMATTER) + ")";
    }
}
