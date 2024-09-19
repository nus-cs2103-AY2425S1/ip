package Gary.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a Deadline task, which has a description and a date by which the task is due.
 */
public class Deadline extends Task {

    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy");
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    // The due date of the deadline task
    private final LocalDate deadline;

    /**
     * Constructs a {@code Deadline} object with the specified description and due date.
     *
     * @param description The description of the deadline task.
     * @param dueDate The due date of the task in "yyyy-MM-dd" format.
     * @throws IllegalArgumentException if the due date is in an invalid format.
     */
    public Deadline(String description, String dueDate) {
        super(description);
        this.deadline = parseDate(dueDate);
    }

    /**
     * Parses the given date string in the format "yyyy-MM-dd" and returns a {@code LocalDate} object.
     *
     * @param dateTime The date string to be parsed.
     * @return A {@code LocalDate} object representing the parsed date.
     * @throws IllegalArgumentException if the date string is in an invalid format.
     */
    private LocalDate parseDate(String dateTime) {
        try {
            return LocalDate.parse(dateTime, INPUT_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format. Expected format: yyyy-MM-dd.");
        }
    }

    /**
     * Returns a string representation of the Deadline task, including its type "[D]", completion status,
     * description, and due date.
     *
     * @return A string in the format "[D][X] description (by: MMM dd yyyy)" if done, or
     *     "[D][ ] description (by: MMM dd yyyy)" if not done.
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.deadline.format(OUTPUT_FORMATTER));
    }

    /**
     * Converts the Deadline task into a string that can be written to a file.
     *
     * @return A string in the format "D | {isDone} | {description} | {dueDate}", where {isDone} is "1"
     *     if done and "0" if not.
     */
    @Override
    public String parseToFile() {
        return String.format("D | %d | %s | %s", this.isDone ? 1 : 0, this.description, this.deadline.format(INPUT_FORMATTER));
    }

    /**
     * Checks if this {@code Deadline} is equal to another object.
     * Two deadlines are considered equal if they have the same description and due date.
     *
     * @param obj The object to compare with this {@code Deadline}.
     * @return {@code true} if the specified object is equal to this {@code Deadline}, {@code false} otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Deadline)) {
            return false;
        }
        Deadline otherDeadline = (Deadline) obj;
        return super.equals(otherDeadline) && this.deadline.equals(otherDeadline.deadline);
    }
}
