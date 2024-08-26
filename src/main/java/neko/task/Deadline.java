package neko.task;
import java.time.LocalDateTime;

/**
 * The Deadline class represents a task with a specified deadline.
 * It extends the Task class and adds a by field for the deadline, represented as
 * LocalDateTime objects. This task type is represented by a "[D]" prefix in its string output.
 *
 * @author  Siow Rui Ming
 * @version 0.1
 * @since   2024-08-19
 */

public class Deadline extends Task {

    private final LocalDateTime by;

    /**
     * Constructs a new Deadline object with the specified name and deadline.
     * @param name the name or description of the task.
     * @param by the date and time of the deadline
     */
    public Deadline(String name, LocalDateTime by) {
        super(name);
        this.by = by;
    }

    /**
     * Returns a string representation of the Deadline task.
     * The string is prefixed with "[D]" to indicate it's a deadline task and ended
     * with a string representation of its deadline.
     *
     * @return a string representation of the Deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(dateFormatter) + ")";
    }

    /**
     * Returns a string representation of the Deadline task's deadline.
     * The string is formatted using the format "eee, d MMM uuuu h:mma".
     * For example, Mon, 26 Aug 2024, at 5:09 PM.
     *
     * @return a string representation of the Deadline task's deadline.
     */
    @Override
    public String getTime() {
        return by.format(dateFormatter);
    }
}
