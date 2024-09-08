package terminator.command;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class to handle and parse user input from standard input.
 */
public class CommandParser {

    // Any ASCII character any number of times
    private static final String ANY_WORDS = "\\p{ASCII}*";

    // dd/MM/YYYY HHmm
    private static final String TASK_DATETIME_FORMAT = "\\d\\d?/\\d\\d?/\\d{4} \\d{4}";

    private static final Pattern LIST_PATTERN = Pattern.compile("^list(\\s+" + ANY_WORDS + ")?$");
    private static final Pattern MARK_PATTERN = Pattern.compile("^mark(\\s+\\d+\\s*$)?");
    private static final Pattern UNMARK_PATTERN = Pattern.compile("^unmark(\\s+\\d+\\s*$)?");
    private static final Pattern TODO_PATTERN = Pattern.compile("^todo(\\s+"
            + ANY_WORDS
            + ")?");
    private static final Pattern EVENT_PATTERN = Pattern.compile("^event(\\s+"
            + ANY_WORDS
            + " /from " + TASK_DATETIME_FORMAT
            + " /to " + TASK_DATETIME_FORMAT
            + "\\s*$)?");
    private static final Pattern DEADLINE_PATTERN = Pattern.compile("^deadline(\\s+"
            + ANY_WORDS
            + " /by " + TASK_DATETIME_FORMAT
            + "\\s*$)?");
    private static final Pattern DELETE_PATTERN = Pattern.compile("^delete(\\s+\\d+\\s*$)?");
    private static final Pattern FIND_PATTERN = Pattern.compile("find(\\s+" + ANY_WORDS + "$)?");
    private static final Pattern HELP_PATTERN = Pattern.compile("^help(\\s+" + ANY_WORDS + ")?$");

    /**
     * Creates a new instance of a CommandParser.
     */
    public CommandParser() {
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
        } else if ((matcher = LIST_PATTERN.matcher(input)).find()) {
            return new ListCommand(matcher.group(1));
        } else if ((matcher = MARK_PATTERN.matcher(input)).find()) {
            return new MarkCommand(matcher.group(1));
        } else if ((matcher = UNMARK_PATTERN.matcher(input)).find()) {
            return new UnmarkCommand(matcher.group(1));
        } else if ((matcher = TODO_PATTERN.matcher(input)).find()) {
            return new TodoCommand(matcher.group(1));
        } else if ((matcher = EVENT_PATTERN.matcher(input)).find()) {
            return new EventCommand(matcher.group(1));
        } else if ((matcher = DEADLINE_PATTERN.matcher(input)).find()) {
            return new DeadlineCommand(matcher.group(1));
        } else if ((matcher = DELETE_PATTERN.matcher(input)).find()) {
            return new DeleteCommand(matcher.group(1));
        } else if ((matcher = FIND_PATTERN.matcher(input)).find()) {
            return new FindCommand(matcher.group(1));
        } else if ((matcher = HELP_PATTERN.matcher(input)).find()) {
            return new HelpCommand(matcher.group(1));
        } else {
            return new UnknownCommand();
        }
    }
}
