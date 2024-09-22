package nah.data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Deadlines class represents a task with a specific deadline.
 * It has a LocalDateTime field to store the deadline.
 *
 */
public class Deadlines extends Task {
    private static final String TIME_PATTERN = "MMM d yyyy, h:mm a";
    private LocalDateTime time;

    /**
     * Constructor.
     * @param content the description of the task
     * @param by deadline of the task
     */
    public Deadlines(String content, LocalDateTime by) {
        super(content);
        assert by != null : "deadline time cannot be null";
        this.time = by;
    }

    /**
     * Checks is the deadline of this task is before due.
     *
     * @return a LocalDateTime value
     */
    @Override
    public boolean isBefore(LocalDateTime due) {
        return this.time.isBefore(due);
    }

    /**
     * Return a brief description of task to be store in a hard disk.
     *
     * @return a String
     */
    @Override
    public String brief() {
        return "D | " + super.getStatus() + " | " + super.getTask() + " | "
                + this.time.format(DateTimeFormatter.ofPattern(TIME_PATTERN));
    }

    /**
     * Checks if this String to lowercase is deadline.
     *
     * @param s the String that need to be checked
     * @return a boolean value
     */
    @Override
    public boolean isReferingToTask(String s) {
        assert s != null : "Task reference cannot be null";
        return s.trim().toLowerCase().equals("deadline");
    }

    /**
     * Returns String representation.
     *
     * @return a String
     */
    @Override
    public String toString() {
        return "[D] "
                + super.toString()
                + " (by: "
                + this.time.format(DateTimeFormatter.ofPattern(TIME_PATTERN))
                + ")";
    }
}
