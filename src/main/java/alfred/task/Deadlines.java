package alfred.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import alfred.exception.AlfredException;

/**
 * Represents a Deadline task in the task management system.
 * A Deadline task has a description and a deadline date.
 */
public class Deadlines extends Task {
    private LocalDate deadline;
    private static final DateTimeFormatter DISPLAY_DATE_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy");

    /**
     * Constructs a Deadlines object with a description and a deadline date.
     *
     * @param description The description of the deadline task.
     * @param deadline    The deadline date in yyyy-MM-dd format.
     * @throws AlfredException If the deadline date is not in the correct format.
     */
    public Deadlines(String description, String deadline) throws AlfredException {
        super(description);
        this.deadline = parseDeadline(deadline);
    }

    /**
     * Constructs a Deadlines object with a description, a deadline date, and a status.
     *
     * @param description The description of the deadline task.
     * @param deadline    The deadline date in yyyy-MM-dd format.
     * @param isDone      The completion status of the task.
     * @throws AlfredException If the deadline date is not in the correct format.
     */
    public Deadlines(String description, String deadline, boolean isDone) throws AlfredException {
        super(description);
        this.isDone = isDone;
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
        return deadline.format(DISPLAY_DATE_FORMAT);
    }

    /**
     * Creates a Deadlines task from the input string.
     * The input should be in the format: deadline task /by yyyy-mm-dd.
     *
     * @param input The input string containing the task details.
     * @return A Deadlines object with the specified description and deadline date.
     * @throws AlfredException If the input string does not match the expected format.
     */
    public static Task createTask(String input) throws AlfredException {
        String[] parsedInput = parseInputForDeadline(input);
        String description = parsedInput[0];
        String deadline = parsedInput[1];

        return new Deadlines(description, deadline);
    }

    /**
     * Parses the input string to extract the task description and deadline.
     * Validates the input format using a regular expression.
     *
     * @param input The input string to be parsed.
     * @return An array where the first element is the description and the second element is the deadline.
     * @throws AlfredException If the input format is incorrect.
     */
    private static String[] parseInputForDeadline(String input) throws AlfredException {
        String regex = "^deadline\\s+(.+?)\\s+/by\\s+(\\d{4}-\\d{2}-\\d{2})$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        if (matcher.matches()) {
            return new String[]{matcher.group(1), matcher.group(2)};
        } else {
            throw new AlfredException("That is the wrong deadline format Sir. It goes deadline <task> "
                    + "/by yyyy-mm-dd");
        }
    }

    /**
     * Returns a string representation of the Deadlines task formatted for saving to a file.
     *
     * @return A string representing the Deadlines task in file format,
     *         including the type, status, description, and deadline.
     */
    @Override
    public String toFileFormat() {
        return "D | " + getStatusIcon() + " | " + description + " | " + deadline;
    }
}
