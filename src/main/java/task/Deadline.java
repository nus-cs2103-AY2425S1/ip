package task;

import java.time.LocalDate;
import java.util.Collection;

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
     * Constructs a {@code Deadline} task with the specified description, deadline date,
     * and associated tags.
     * <p>
     * This constructor initializes the task with the given description, deadline date,
     * and associated tags. The deadline date is parsed from a string into a {@link LocalDate} object.
     * </p>
     *
     * @param description The description of the task.
     * @param by          The deadline date of the task in string format (e.g., "2024-09-30").
     * @param tags        A collection of tags associated with the task.
     */
    public Deadline(String description, String by, Collection<String> tags) {
        super(description, tags);
        this.by = LocalDate.parse(by);
    }

    /**
     * Constructs a {@code Deadline} task with the specified description, deadline date,
     * completion status, and associated tags.
     * <p>
     * This constructor initializes the task with the given description, deadline date,
     * completion status, and associated tags. The deadline date is parsed from a string into
     * a {@link LocalDate} object.
     * </p>
     *
     * @param description The description of the task.
     * @param by          The deadline date of the task in string format (e.g., "2024-09-30").
     * @param isDone      A boolean indicating whether the task is completed.
     * @param tags        A collection of tags associated with the task.
     */
    public Deadline(String description, String by, boolean isDone, Collection<String> tags) {
        super(description, isDone, tags);
        this.by = LocalDate.parse(by);
    }

    /**
     * Returns a string representation of the {@code Deadline} task.
     * <p>
     * The string format is "[D][task details] (by: [deadline date]) [#tags]". The deadline date
     * is formatted for display purposes. Tags are appended at the end of the string,
     * with each tag prefixed by a '#'.
     * </p>
     *
     * @return A string representation of the {@code Deadline} task, including its tags.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getDateStringPrintFormat(this.by) + ") " + this.tags.toString();
    }


    /**
     * Returns a string representation of the {@code Deadline} task suitable for database storage.
     * <p>
     * The string format is "D | [isDone] | [description] | [deadline date] | [tags]". The deadline date
     * is formatted for storage purposes, and tags are added at the end.
     * </p>
     *
     * @return A string representation of the {@code Deadline} task suitable for database storage.
     */
    @Override
    public String getDatabaseString() {
        return String.format(
                "D | %d | %s | %s | %s",
                this.isDone ? 1 : 0,
                this.description,
                getDateStringStorageFormat(this.by),
                this.tags.getDatabaseString()
        );
    }
}