package stelle.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This class represents a stelle.task.Deadline. It is child of the stelle.task.Task class.
 * @author Lee Ze Hao (A0276123J)
 */

public class Deadline extends Task {
    private static final String DATE_TIME_INPUT_FORMATTER_PATTERN = "yyyy-MM-dd HH:mm";
    private static final String DATE_TIME_OUTPUT_FORMATTER_PATTERN = "d LLLL yyyy HH:mm";
    private static final DateTimeFormatter dateTimeInputFormatter = DateTimeFormatter
            .ofPattern(DATE_TIME_INPUT_FORMATTER_PATTERN);
    private static final DateTimeFormatter dateTimeOutputFormatter = DateTimeFormatter
            .ofPattern(DATE_TIME_OUTPUT_FORMATTER_PATTERN);
    private LocalDateTime by;

    /**
     * Constructor for a stelle.task.Deadline object. Sets name upon creation.
     * @param name Name of stelle.task.Deadline object.
     * @param by The date the deadline must be accomplished by.
     */
    public Deadline(String name, LocalDateTime by) {
        super(name, TaskType.DEADLINE);
        this.by = by;
    }

    /**
     * Returns a string containing the time the deadline must be completed by.
     * @return String A string containing the time the deadline must be completed by.
     */
    public String getByTime() {
        return this.by.format(dateTimeInputFormatter);
    }

    /**
     * Returns string representation of the stelle.task.Deadline.
     * @return String containing indication of stelle.task.Deadline class,
     *      and string representation of parent stelle.task.Task class.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + by.format(dateTimeOutputFormatter) + ")";
    }
}
