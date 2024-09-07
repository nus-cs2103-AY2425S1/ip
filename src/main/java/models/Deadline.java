package models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a deadline in the task management system.
 * A {@code Deadline} task has a description, a deadline date, and a completion status.
 * It also handles parsing and formatting of the deadline date.
 *
 * <p>This class extends the {@code Task} class and adds functionality for handling
 * tasks with deadlines.</p>
 */
public class Deadline extends Task {

    private LocalDate by;

    /**
     * Constructs a {@code Deadline} with the specified description.
     * The task is initialized as not completed and without a deadline.
     *
     * @param description The description of the task.
     */
    public Deadline(String description) {
        super(description);
    }

    /**
     * Converts a string into a {@code LocalDate} object.
     * The input string should be in the format "yyyy-MM-dd".
     * If the input cannot be parsed, the method returns {@code LocalDate.EPOCH}.
     *
     * @param potentialDate The string representing the date.
     * @return A {@code LocalDate} representing the parsed date, or {@code LocalDate.EPOCH} if parsing fails.
     */
    public LocalDate convertDate(String potentialDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            return LocalDate.parse(potentialDate, formatter);
        } catch (DateTimeParseException e) {
            return LocalDate.EPOCH;  // Handle as needed
        }
    }

    /**
     * Constructs a {@code Deadline} with the specified description and deadline date.
     * The task is initialized as not completed.
     *
     * @param description The description of the task.
     * @param by The deadline date as a string in the format "yyyy-MM-dd".
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = convertDate(by);
    }

    /**
     * Constructs a {@code Deadline} with the specified description, completion status, and deadline date.
     *
     * @param description The description of the task.
     * @param isDone The completion status of the task; {@code true} if the task is completed, {@code false} otherwise.
     * @param by The deadline date as a string in the format "yyyy-MM-dd".
     */
    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        this.by = convertDate(by);
    }

    /**
     * Returns the deadline date of the task.
     *
     * @return The {@code LocalDate} representing the deadline.
     */
    public LocalDate getBy() {
        return this.by;
    }

    /**
     * Serializes the deadline task into a string format for saving in the database.
     * The format is "D|<isDone>|<description>|<by>", where {@code isDone} is 1 if the task is completed, and 0 otherwise.
     *
     * @return A serialized string representation of the deadline task.
     */
    public String serialize() {
        return String.format("D|%s|%s|%s", this.getIsDone() ? "1" : "0", this.getDescription(),
                this.getBy());
    }

    /**
     * Returns the string representation of the deadline task.
     * The format is "[D][<statusIcon>] <description> (by: <by>)", where {@code statusIcon} is "X" if the task is completed, or a space otherwise.
     * The deadline date is formatted as "MMM d yyyy".
     *
     * @return A string representing the deadline task for display.
     */
    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)", this.getStatusIcon(), this.getDescription(),
                this.getBy().format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}
