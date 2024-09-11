package Johnson.parser;

import Johnson.command.*;
import Johnson.exceptions.MissingDateException;
import Johnson.exceptions.MissingDividerException;
import Johnson.exceptions.MissingTaskException;
import Johnson.exceptions.UnknownCommandException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parses user input into commands.
 * This class is responsible for interpreting the user's input and
 * converting it into executable commands.
 */
public class Parser {

    /**
     * Pattern to match basic commands.
     */
    private static final Pattern BASIC_COMMAND_PATTERN =
            Pattern.compile("(?<command>\\S+)(?<arguments>.*)");

    /**
     * Pattern to match arguments with date and time.
     */
    private static final Pattern DATETIME_COMMAND_PATTERN =
            Pattern.compile("(?<task>[^/]+)\\s*/\\s*(?<date>\\d{4}[-/]\\d{2}[-/]\\d{2})\\s+(?<time>\\d{2}:\\d{2})");

    /**
     * Pattern to match arguments with date only.
     */
    private static final Pattern DATE_COMMAND_PATTERN =
            Pattern.compile("(?<task>[^/]+)\\s*/\\s*(?<date>\\d{4}[-/]\\d{2}[-/]\\d{2})\\s");

    /**
     * Pattern to match commands with an index.
     */
    private static final Pattern INDEXED_COMMAND_PATTERN =
            Pattern.compile("\\s*(?<index>\\d+)\\s*");

    public Parser() {
    }

    /**
     * Parses the user input into a command.
     *
     * @param userInput the input entered by the user.
     * @return the parsed command.
     */
    public Command parseCommand(String userInput) throws MissingTaskException, MissingDateException, MissingDividerException, UnknownCommandException {
        Matcher matcher = BASIC_COMMAND_PATTERN.matcher(userInput.trim());

        if (!matcher.matches()) {
            //Invalid command exception
            return null;
        }

        String command = matcher.group("command");
        String arguments = matcher.group("arguments");
        Command parsedCommand = null;

        switch (command) {
        case ToDoCommand.COMMAND_WORD:
            parsedCommand = prepToDo(arguments);
            break;
        case ListCommand.COMMAND_WORD:
            parsedCommand = new ListCommand();
            break;
        case DeadlineCommand.COMMAND_WORD:
             parsedCommand = prepDeadline(arguments);
             break;
        case MarkCommand.COMMAND_WORD:
            parsedCommand = new MarkCommand(prepIndexedCommand(arguments));
            break;
        case UnmarkCommand.COMMAND_WORD:
            parsedCommand = new UnmarkCommand(prepIndexedCommand(arguments));
            break;
        case EventCommand.COMMAND_WORD:
            parsedCommand = prepEvent(arguments);
            break;
        case ExitCommand.COMMAND_WORD:
            parsedCommand = new ExitCommand();
            break;
        case DeleteCommand.COMMAND_WORD:
            parsedCommand = new DeleteCommand(prepIndexedCommand(arguments));
            break;
        case FindCommand.COMMAND_WORD:
            parsedCommand = new FindCommand(arguments);
            break;
        default:
            throw new UnknownCommandException("Unknown command");
        }
        return parsedCommand;
    }

    /**
     * Prepares a DeadlineCommand from the given arguments.
     *
     * @param arguments the arguments for the deadline command.
     * @return the prepared DeadlineCommand.
     * @throws MissingDividerException if the divider is missing.
     * @throws MissingTaskException if the task is missing.
     * @throws MissingDateException if the date is missing.
     */
    private Command prepDeadline(String arguments) throws
            MissingDividerException, MissingTaskException, MissingDateException, UnknownCommandException {

        if (!arguments.contains("/")) {
            throw new MissingDividerException("missing a divider");
        }

        Matcher deadlineMatcher;
        String task = null;
        String date = null;
        String time = null;

        if (DATETIME_COMMAND_PATTERN.matcher(arguments).matches()) {
            deadlineMatcher  = DATETIME_COMMAND_PATTERN.matcher(arguments);
            task = deadlineMatcher.group("task");
            date = deadlineMatcher.group("date");
            time = deadlineMatcher.group("time");
        } else if (DATE_COMMAND_PATTERN.matcher(arguments).matches()) {
            deadlineMatcher = DATE_COMMAND_PATTERN.matcher(arguments);
            task = deadlineMatcher.group("task");
            date = deadlineMatcher.group("date");
        } else {
            throw new UnknownCommandException("Unknown command");
        }

        if (task == null) {
            throw new MissingTaskException("missing a task");
        }
        if (date == null) {
            throw new MissingDateException("missing a date");
        }

        return new DeadlineCommand(task, date, time);
    }

    /**
     * Prepares an EventCommand from the given arguments.
     *
     * @param arguments the arguments for the event command.
     * @return the prepared EventCommand.
     * @throws MissingTaskException if the task is missing.
     * @throws MissingDividerException if the divider is missing.
     * @throws MissingDateException if the date is missing.
     */
    private Command prepEvent(String arguments) throws
            MissingTaskException, MissingDividerException, MissingDateException, UnknownCommandException {

        if (!arguments.contains("/")) {
            throw new MissingDividerException("missing a divider");
        }

        Matcher eventMatcher;
        String task = null;
        String date = null;
        String time = null;

        if (DATETIME_COMMAND_PATTERN.matcher(arguments).matches()) {
            eventMatcher = DATETIME_COMMAND_PATTERN.matcher(arguments);
            task = eventMatcher.group("task");
            date = eventMatcher.group("date");
            time = eventMatcher.group("time");
        } else if (DATE_COMMAND_PATTERN.matcher(arguments).matches()) {
            eventMatcher = DATE_COMMAND_PATTERN.matcher(arguments);
            task = eventMatcher.group("task");
            date = eventMatcher.group("date");
        } else {
            throw new UnknownCommandException("Unknown command");
        }

        if (task == null) {
            throw new MissingTaskException("missing a task");
        }
        if (date == null) {
            throw new MissingDateException("missing a date");
        }
        return new EventCommand(task, date, time);
    }

    /**
     * Prepares an indexed command from the given arguments.
     *
     * @param arguments the arguments for the indexed command.
     * @return the index parsed from the arguments.
     */
    private int prepIndexedCommand(String arguments) {
        Matcher indexMatcher = INDEXED_COMMAND_PATTERN.matcher(arguments);
        if (indexMatcher.matches()) {
            String index = indexMatcher.group("index");
            return Integer.parseInt(index);
        } else {
            return -1;
        }
    }

    /**
     * Prepares a ToDoCommand from the given arguments.
     *
     * @param arguments the arguments for the todo command.
     * @return the prepared ToDoCommand.
     * @throws MissingTaskException if the task is missing.
     */
    private Command prepToDo(String arguments) throws MissingTaskException {
        if (arguments == null || arguments.isEmpty()) {
            throw new MissingTaskException("Missing a task to add!");
        }
        else {
            return new ToDoCommand(arguments);
        }
    }
}
