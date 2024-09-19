package Gary.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task, which has a description and a date by which the task is due.
 */
public class Deadline extends Task {

    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy");

    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private LocalDate deadline; // The due date of the deadline task

    /**
     * Constructs a {@code Deadline} object with the specified description and due date.
     *
     * @param description The description of the deadline task.
     * @param dueDate The due date of the task in "yyyy-MM-dd" format.
     */
    public Deadline(String description, String dueDate) {
        super(description);
        this.deadline = parseDate(dueDate);
    }

    /**
     * Parses the given date string in the format "yyyy-MM-dd" and returns a LocalDate object.
     *
     * @param dateTime The date string to be parsed.
     * @return A LocalDate object representing the parsed date.
     */
    private LocalDate parseDate(String dateTime) {
        return LocalDate.parse(dateTime, INPUT_FORMATTER);
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
        return "[D][" + (this.isDone ? "X" : " ") + "] " + this.description
                + " (by: " + this.deadline.format(OUTPUT_FORMATTER) + ")";
    }

    /**
     * Converts the Deadline task into a string that can be written to a file.
     *
     * @return A string in the format "D | {isDone} | {description} | {dueDate}", where{isDone} is "1"
     *     if done and "0" if not.
     */
    @Override
    public String parseToFile() {
        return "D | " + (this.isDone ? "1" : "0") + " | " + this.description + " | "
                + this.deadline.format(INPUT_FORMATTER);
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
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        Deadline otherDeadline = (Deadline) obj;
        return super.equals(otherDeadline) && this.deadline.equals(otherDeadline.deadline);
    }
}

