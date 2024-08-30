package Tasks;

import enums.TaskType;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a specific deadline.
 * This class extends the {@link Task} class, adding a deadline date to the basic task structure.
 */
public class Deadline extends Task {
    protected LocalDate deadline;  // Date of the deadline.

    /**
     * Constructs a Deadline task with a description and a deadline date.
     *
     * @param description The textual description of the deadline task.
     * @param deadline The deadline date in 'yyyy-MM-dd' format.
     */
    public Deadline(String description, String deadline) {
        super(description, TaskType.DEADLINE);
        this.deadline = LocalDate.parse(deadline, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    /**
     * Returns the formatted deadline date as a string.
     *
     * @return The deadline date in 'yyyy-MM-dd' format.
     */
    public String getDeadline() {
        return deadline.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    /**
     * Returns the string representation of this deadline task in a format suitable for file storage.
     * This includes the type indicator 'D', followed by the base task's file format, and the deadline date.
     *
     * @return The formatted string suitable for file storage.
     */
    @Override
    public String toFileFormat() {
        return "D" + super.toFileFormat() + " | " + deadline.format(DateTimeFormatter.ISO_LOCAL_DATE);
    }

    /**
     * Returns the string representation of the deadline task, including its status, description,
     * and deadline date formatted as 'MMM dd yyyy'.
     *
     * @return The detailed string representation of the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
