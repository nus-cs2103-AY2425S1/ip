import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/** This class represents a Deadline. It is child of the Task class.
 * @author Lee Ze Hao (A0276123J)
 */

public class Deadline extends Task {
    private static final String DATE_TIME_FORMATTER_PATTERN = "yyyy-MM-dd HH:mm";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMATTER_PATTERN);
    private LocalDateTime by;

    /** Constructor for a Deadline object. Sets name upon creation.
     * @param name Name of Deadline object.
     * @param by A string containing the date the deadline must be accomplished by.
     */
    public Deadline(String name, String by) {
        super(name, TASK_TYPE.DEADLINE);
        this.by = LocalDateTime.parse(by);
    }

    /**
     * Returns a string containing the time the deadline must be completed by.
     * @return String A string containing the time the deadline must be completed by.
     */
    public String getByTime() {
        return this.by.format(formatter);
    }

    /** Returns string representation of the Deadline.
     * @return String containing indication of Deadline class, and string representation of parent Task class.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " +
                by.format(DateTimeFormatter.RFC_1123_DATE_TIME).replace(" GMT", "") + ")";
    }
}
