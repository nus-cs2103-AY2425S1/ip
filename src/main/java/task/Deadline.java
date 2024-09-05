package task;

import java.time.LocalDate;

/**
 * Represents a task with a deadline.
 * <p>
 * The {@code Deadline} class extends {@link Task} to include a deadline date.
 * It provides methods to represent the task with its deadline as a string and
 * to retrieve a formatted string suitable for database storage.
 * </p>
 */
public class Deadline extends Task {

    protected LocalDate by;

    /**
     * Constructs a {@code Deadline} task with the specified description and deadline date.
     * <p>
     * This constructor initializes the task with the given description and deadline date.
     * The deadline date is parsed from a string into a {@link LocalDate} object.
     * </p>
     *
     * @param description The description of the task.
     * @param by          The deadline date of the task in string format (e.g., "2024-09-30").
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by);
    }

    /**
     * Constructs a {@code Deadline} task with the specified description, deadline date,
     * and completion status.
     * <p>
     * This constructor initializes the task with the given description, deadline date,
     * and whether the task is completed. The deadline date is parsed from a string into
     * a {@link LocalDate} object.
     * </p>
     *
     * @param description The description of the task.
     * @param by          The deadline date of the task in string format (e.g., "2024-09-30").
     * @param isDone      A boolean indicating whether the task is completed.
     */
    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = LocalDate.parse(by);
    }

    /**
     * Returns a string representation of the {@code Deadline} task.
     * <p>
     * The string format is "[D][task details] (by: [deadline date])". The deadline date
     * is formatted for display purposes.
     * </p>
     *
     * @return A string representation of the {@code Deadline} task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getDateStringPrintFormat(this.by) + ")";
    }

    /**
     * Returns a string representation of the {@code Deadline} task suitable for database storage.
     * <p>
     * The string format is "D | [isDone] | [description] | [deadline date]". The deadline date
     * is formatted for storage purposes.
     * </p>
     *
     * @return A string representation of the {@code Deadline} task suitable for database storage.
     */
    @Override
    public String getDatabaseString() {
        return String.format(
                "D | %d | %s | %s",
                this.isDone ? 1 : 0,
                this.description,
                getDateStringStorageFormat(this.by)
        );
    }
}