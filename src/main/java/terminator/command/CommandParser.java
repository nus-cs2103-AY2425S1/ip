package terminator.command;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class to handle and parse user input from standard input.
 */
public class CommandParser {
    private final Pattern listPattern;
    private final Pattern markPattern;
    private final Pattern unmarkPattern;
    private final Pattern todoPattern;
    private final Pattern eventPattern;
    private final Pattern deadlinePattern;
    private final Pattern deletePattern;

    // Any ASCII character any number of times
    private static final String ANY_WORDS = "\\p{ASCII}*";

    // dd/MM/YYYY HHmm
    private static final String TASK_DATETIME_FORMAT = "\\d\\d?/\\d\\d?/\\d{4} \\d{4}";

    public CommandParser() {
        this.listPattern = Pattern.compile("^list(\\s+" + ANY_WORDS + ")?$");
        this.markPattern = Pattern.compile("^mark(\\s+\\d+\\s*$)?");
        this.unmarkPattern = Pattern.compile("^unmark(\\s+\\d+\\s*$)?");
        this.todoPattern = Pattern.compile("^todo(\\s+"
                + ANY_WORDS
                + ")?");
        this.eventPattern = Pattern.compile("^event(\\s+"
                + ANY_WORDS
                + " /from " + TASK_DATETIME_FORMAT
                + " /to " + TASK_DATETIME_FORMAT
                + "\\s*$)?");
        this.deadlinePattern = Pattern.compile("^deadline(\\s+"
                + ANY_WORDS
                + " /by " + TASK_DATETIME_FORMAT
                + "\\s*$)?");
        this.deletePattern = Pattern.compile("^delete(\\s+\\d+\\s*$)?");
    }

    /**
     * Parses the user input and returns an instance of a concrete implementation of the Command class,
     * which can be executed to perform the desired operation.
     *
     * @param input The user input to parse
     * @return An instance of the Command class.
     */
    public Command parse(String input) {
        Matcher matcher;
        if (input.isEmpty()) {
            return new EmptyCommand();
        } else if ((matcher = listPattern.matcher(input)).find()) {
            return new ListCommand(matcher.group(1));
        }  else if ((matcher = markPattern.matcher(input)).find()) {
            return new MarkCommand(matcher.group(1));
        } else if ((matcher = unmarkPattern.matcher(input)).find()) {
            return new UnmarkCommand(matcher.group(1));
        } else if ((matcher = todoPattern.matcher(input)).find()) {
            return new TodoCommand(matcher.group(1));
        } else if ((matcher = eventPattern.matcher(input)).find()) {
            return new EventCommand(matcher.group(1));
        } else if ((matcher = deadlinePattern.matcher(input)).find()) {
            return new DeadlineCommand(matcher.group(1));
        } else if ((matcher = deletePattern.matcher(input)).find()) {
            return new DeleteCommand(matcher.group(1));
        } else return new UnknownCommand();
    }
}
