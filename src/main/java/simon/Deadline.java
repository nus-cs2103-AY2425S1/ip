package simon;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
 * Represents a task with a specific deadline in the Simon application.
 * Inherits from the Task class and adds a deadline property.
 */

public class Deadline extends Task {
    private static final DateTimeFormatter SAVE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter DISPLAY_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
    private LocalDateTime deadline;

    /**
     * Constructs a Deadline task with the specified name, number, and deadline.
     *
     * @param name the name of the task
     * @param number the number of the task
     * @param deadline the deadline of the task as a LocalDateTime object
     */
    public Deadline(String name, int number, LocalDateTime deadline) {
        super(name, number);
        this.deadline = deadline;
    }
    /**
     * Returns a string representation of the Deadline task for display purposes.
     *
     * @return a string representation of the Deadline task in the format "[D][task details] (by: deadline)"
     */
    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by: " + deadline.format(DISPLAY_FORMATTER) + ")";
    }

    /**
     * Returns a string representation of the Deadline task in the format used for saving to storage.
     *
     * @return a string representation of the Deadline task in the format "D | completed | name | deadline"
     */
    @Override
    public String toSaveFormat() {
        return "D | " + (super.getCompleted() ? 1 : 0) + " | " + super.getName() + " | "
                + deadline.format(SAVE_FORMATTER);
    }
    /**
     * Parses a Deadline object from a string representation in the save format.
     *
     * @param name the name of the task
     * @param number the number of the task
     * @param deadlineString the string representation of the deadline in the save format
     * @return a Deadline object created from the provided string representation
     */

    public static Deadline parseFromString(String name, int number, String deadlineString) {
        LocalDateTime deadline = LocalDateTime.parse(deadlineString, SAVE_FORMATTER);
        return new Deadline(name, number, deadline);
    }
}
