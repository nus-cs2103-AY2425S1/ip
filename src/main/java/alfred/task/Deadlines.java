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

    /**
     * Constructs a Deadlines object with a description and a deadline date.
     *
     * @param description The description of the deadline task.
     * @param deadline    The deadline date in yyyy-MM-dd format.
     * @throws AlfredException If the deadline date is not in the correct format.
     */
    public Deadlines(String description, String deadline) throws AlfredException {
        super(description);
        try {
            this.deadline = LocalDate.parse(deadline);
        } catch (DateTimeParseException e) {
            throw new AlfredException("That is not a valid deadline Sir. It should go yyyy-mm-dd.");
        }
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
        try {
            this.deadline = LocalDate.parse(deadline);
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
        return "[D]" + super.toString() + " (by: "
                + deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
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
        String regex = "^deadline\\s+(.+?)\\s+/by\\s+(\\d{4}-\\d{2}-\\d{2})$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        if (matcher.matches()) {
            String description = matcher.group(1);
            String deadline = matcher.group(2);

            return new Deadlines(description, deadline);
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
