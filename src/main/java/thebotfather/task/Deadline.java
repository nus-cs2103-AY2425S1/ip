package thebotfather.task;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

import thebotfather.util.TheBotFatherException;

/**
 * Represents a task with a deadline.
 * A Deadline is a type of Task that has a specific date and time by which it must be completed.
 */
public class Deadline extends Task {

    /**
     * The deadline date and time by which the task should be completed.
     */
    private final LocalDateTime by;

    /**
     * Constructs a Deadline task with the specified description and deadline date/time.
     *
     * @param description The description of the task.
     * @param by          The date and time by which the task must be completed.
     * @throws TheBotFatherException If the task description or deadline is invalid.
     */
    public Deadline(String description, LocalDateTime by) throws TheBotFatherException {
        super(description, "D");
        assert description != null && !description.trim().isEmpty() : "Description cannot be null or empty";
        assert by != null : "Time ('by') cannot be null";
        this.by = by;

    }

    /**
     * Creates a new Deadline object from a StringTokenizer input.
     * The input should contain the task description followed by "/by" and the deadline date/time.
     *
     * @param tokens The StringTokenizer containing the input string.
     * @return A new Deadline object.
     * @throws TheBotFatherException If the input format is incorrect or if the deadline date/time is invalid.
     */
    public static Deadline makeDeadline(StringTokenizer tokens) throws TheBotFatherException {
        try {
            StringBuilder description = new StringBuilder();
            StringBuilder by = new StringBuilder();

            String token = tokens.nextToken();
            while (!token.equals("/by")) {
                description.append(token).append(" ");
                token = tokens.nextToken();
            }

            token = tokens.nextToken();
            by.append(token).append(" ");
            while (tokens.hasMoreTokens()) {

                token = tokens.nextToken();
                by.append(token).append(" ");
            }

            return new Deadline(description.toString().trim(),
                    LocalDateTime.parse(by.toString().trim(),
                            Task.DATE_STRING_FORMATTER));
        } catch (NoSuchElementException | DateTimeException e) {
            throw new TheBotFatherException("If you have a deadline, type : "
                    + "\"deadline <description> /by DD-MM-YY HH:MM\"");
        }
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + this.by.format(Task.DATE_STRING_FORMATTER_PRINT) + ")";
    }

    @Override
    public String toFile() {
        return super.toFile() + " | " + this.by;
    }
}
