package alfred.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import alfred.exception.AlfredException;

/**
 * Represents an event task with a description, start date, and end date.
 * This class extends the <code>Task</code> class to include specific handling
 * for tasks that have a date range.
 */
public class Events extends Task {
    private LocalDate from;
    private LocalDate to;

    /**
     * Constructs a new <code>Events</code> task with the specified description, start date, and end date.
     * The dates should be in the format yyyy-mm-dd. If the dates are invalid, an <code>AlfredException</code>
     * is thrown.
     *
     * @param description Description of the event.
     * @param from Start date of the event in yyyy-mm-dd format.
     * @param to End date of the event in yyyy-mm-dd format.
     * @throws AlfredException If the date format is invalid.
     */
    public Events(String description, String from, String to) throws AlfredException {
        super(description);
        try {
            this.from = LocalDate.parse(from);
            this.to = LocalDate.parse(to);
        } catch (DateTimeParseException e) {
            throw new AlfredException("There was an invalid deadline Sir. It should go yyyy-mm-dd.");
        }
    }

    /**
     * Constructs a new <code>Events</code> task with the specified description, start date, end date,
     * and completion status. The dates should be in the format yyyy-mm-dd. If the dates are invalid,
     * an <code>AlfredException</code> is thrown.
     *
     * @param description Description of the event.
     * @param from Start date of the event in yyyy-mm-dd format.
     * @param to End date of the event in yyyy-mm-dd format.
     * @param isDone Completion status of the task.
     * @throws AlfredException If the date format is invalid.
     */
    public Events(String description, String from, String to, boolean isDone) throws AlfredException {
        super(description);
        try {
            this.from = LocalDate.parse(from);
            this.to = LocalDate.parse(to);
        } catch (DateTimeParseException e) {
            throw new AlfredException("There was an invalid deadline Sir. It should go yyyy-mm-dd.");
        }
        this.isDone = isDone;
    }

    /**
     * Returns a string representation of the <code>Events</code> task, including its status, description,
     * start date, and end date. The dates are formatted as "MMM d yyyy".
     *
     * @return A string representing the <code>Events</code> task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " to: " + to.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * Creates an <code>Events</code> task from the given input string.
     * The input must match the format: event description /from yyyy-mm-dd /to yyyy-mm-dd.
     * If the input is not in the correct format,
     * an <code>AlfredException</code> is thrown.
     *
     * @param input The input string containing event details.
     * @return An <code>Events</code> task created from the input string.
     * @throws AlfredException If the input string does not match the expected format.
     */
    public static Task createTask(String input) throws AlfredException {
        String regex = "^event\\s+(.+?)\\s+/from\\s+"
                + "(\\d{4}-\\d{2}-\\d{2})\\s+/to\\s+(\\d{4}-\\d{2}-\\d{2})$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        if (matcher.matches()) {
            String description = matcher.group(1);
            String from = matcher.group(2);
            String to = matcher.group(3);

            return new Events(description, from, to);
        } else {
            throw new AlfredException("That is the wrong events format Sir. It goes event <task> "
                    + "/from yyyy-mm-dd /to yyyy-mm-dd");
        }
    }

    /**
     * Returns the file format representation of the <code>Events</code> task.
     * The format is "E | status | description | from | to", where status is the task's
     * completion status, description is the task's description, from is the start date, and
     * to is the end date.
     *
     * @return A string representing the <code>Events</code> task in file format.
     */
    @Override
    public String toFileFormat() {
        return "E | " + getStatusIcon() + " | " + description + " | " + from + " | " + to;
    }
}
