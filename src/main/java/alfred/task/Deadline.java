package alfred.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import alfred.exception.AlfredException;

/**
 * Represents a Deadline task in the task management system.
 * A Deadline task has a description and a deadline date.
 */
public class Deadline extends Task {
    private static final DateTimeFormatter DEADLINE_DATE_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy");
    private LocalDate deadline;

    /**
     * Constructs a Deadlines object with a description and a deadline date.
     *
     * @param description The description of the deadline task.
     * @param deadline    The deadline date in yyyy-MM-dd format.
     * @throws AlfredException If the deadline date is not in the correct format.
     */
    public Deadline(String description, String deadline) throws AlfredException {
        super(description);
        this.deadline = parseDeadline(deadline);
    }

    /**
     * Constructs a Deadlines object with a description, a deadline date, and a status.
     *
     * @param description The description of the deadline task.
     * @param deadline    The deadline date in yyyy-MM-dd format.
     * @param isDone      The completion status of the task.
     * @param tags        A list of tags associated with the task.
     * @throws AlfredException If the deadline date is not in the correct format.
     */
    public Deadline(String description, String deadline, boolean isDone, List<String> tags) throws AlfredException {
        super(description, isDone, tags);
        this.deadline = parseDeadline(deadline);
    }

    /**
     * Parses the deadline from a string and validates the date format.
     *
     * @param deadline The deadline string in yyyy-MM-dd format.
     * @return The parsed LocalDate object.
     * @throws AlfredException If the date format is incorrect.
     */
    private LocalDate parseDeadline(String deadline) throws AlfredException {
        try {
            return LocalDate.parse(deadline);
        } catch (DateTimeParseException e) {
            throw new AlfredException("That is not a valid deadline Sir. It should go yyyy-mm-dd.");
        }
    }

    /**
     * Returns a string representation of the Deadlines task for display.
     *
     * @return A string representation of the Deadline task, including the description and deadline date.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + formatDeadlineForDisplay() + ")";
    }

    /**
     * Formats the deadline for display purposes in a user-friendly format.
     *
     * @return A formatted string representation of the deadline.
     */
    private String formatDeadlineForDisplay() {
        return deadline.format(DEADLINE_DATE_FORMAT);
    }

    /**
     * Returns a string representation of the Deadlines task formatted for saving to a file.
     *
     * @return A string representing the Deadlines task in file format,
     *         including the type, status, tags, description, and deadline.
     */
    @Override
    public String toFileFormat() {
        return "D | " + getStatusIcon() + " | " + getTagsAsString() + " | " + description + " | " + deadline;
    }
}
