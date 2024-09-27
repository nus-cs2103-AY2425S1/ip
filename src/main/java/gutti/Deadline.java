package gutti;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

/**
 * The {@code Deadline} class represents a task that has a specific deadline by which it needs to be completed.
 * <p>
 * It extends the {@code Task} class and is marked with a "D" to indicate that it is a Deadline task.
 * </p>
 */
public class Deadline extends Task {

    /** List of date/time formatters to parse various input formats for the deadline. */
    private static final List<DateTimeFormatter> FORMATTERS = Arrays.asList(
            DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"),
            DateTimeFormatter.ofPattern("d/MM/yyyy HHmm"),
            DateTimeFormatter.ofPattern("MMM dd yyyy h:mma"),
            DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm")
    );

    /** The deadline by which the task should be completed. */
    protected LocalDateTime by;

    /**
     * Constructs a new {@code Deadline} task with the specified description and deadline.
     *
     * @param description The description of the Deadline task.
     * @param by The deadline by which the task should be completed.
     */
    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = parseDateTime(by);
    }
    public Deadline(String description, String by, boolean isDone,LocalDateTime markedTime) {
        super(description, isDone,markedTime);
        this.by = parseDateTime(by);
    }

    /**
     * Parses the date/time string into a {@code LocalDateTime} object using multiple predefined formats.
     * If none of the formats match, returns {@code null}.
     *
     * @param by The date/time string to parse.
     * @return The parsed {@code LocalDateTime} object or {@code null} if parsing fails.
     */
    private LocalDateTime parseDateTime(String by) {
        for (DateTimeFormatter formatter : FORMATTERS) {
            try {
                return LocalDateTime.parse(by, formatter);
            } catch (DateTimeParseException e) {
                // Continue trying other formats
            }
        }
        System.out.println("Invalid date format. Please use yyyy-MM-dd HHmm,d/MM/yyyy HHmm"
                + ", dd/MM/yyyy HHmm format or MMM dd yyyy h:mma.");
        return null; // Return null if no format matched
    }

    /**
     * Returns a string representation of the {@code Deadline} task.
     * <p>
     * The string includes the task type identifier "[D]" followed by the status icon,
     * description, and the deadline by which the task needs to be completed.
     * </p>
     *
     * @return A string representation of the Deadline task.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy h:mma");
        return "[D][" + (isDone ? "X" : " ") + "] " + description
                + " (by: " + (by != null ? by.format(formatter) : "Invalid date") + ")";

    }
}
