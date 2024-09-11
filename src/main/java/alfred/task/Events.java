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
    private static final String VALID_EVENT_FORMAT = "^event\\s+(.+?)\\s+/from\\s+"
            + "(\\d{4}-\\d{2}-\\d{2})\\s+/to\\s+(\\d{4}-\\d{2}-\\d{2})$";
    private static final DateTimeFormatter EVENT_DATE_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy");
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
        this.from = parseDate(from);
        this.to = parseDate(to);
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
        this.from = parseDate(from);
        this.to = parseDate(to);
        this.isDone = isDone;
    }

    /**
     * Validates and parses a date string into a LocalDate object.
     * @param date The date string in yyyy-mm-dd format.
     * @return A LocalDate object representing the date.
     * @throws AlfredException If the date format is invalid.
     */
    private static LocalDate parseDate(String date) throws AlfredException {
        try {
            return LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            throw new AlfredException("There was an invalid deadline Sir. It should go yyyy-mm-dd.");
        }
    }

    /**
     * Returns a string representation of the <code>Events</code> task, including its status, description,
     * start date, and end date. The dates are formatted as "MMM d yyyy".
     *
     * @return A string representing the <code>Events</code> task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + formatDate(from) + " to: "
                + formatDate(to) + ")";
    }

    /**
     * Formats a LocalDate object into a string with the format MMM d yyyy.
     * @param date The LocalDate to be formatted.
     * @return A formatted string representing the date.
     */
    private static String formatDate(LocalDate date) {
        return date.format(EVENT_DATE_FORMAT);
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
        String[] parsedInput = parseInputForEvent(input);
        String description = parsedInput[0];
        String from = parsedInput[1];
        String to = parsedInput[2];
        return new Events(description, from, to);
    }

    /**
     * Parses the input string to extract the task description, from and to dates.
     * Validates the input format using a regular expression.
     *
     * @param input The input string to be parsed.
     * @return An array where the first element is the description, second from and third to.
     * @throws AlfredException If the input format is incorrect.
     */
    private static String[] parseInputForEvent(String input) throws AlfredException {
        Pattern pattern = Pattern.compile(VALID_EVENT_FORMAT);
        Matcher matcher = pattern.matcher(input);

        if (matcher.matches()) {
            return new String[]{matcher.group(1), matcher.group(2), matcher.group(3)};
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
