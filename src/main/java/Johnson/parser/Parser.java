package Johnson.parser;

import Johnson.command.Command;
import Johnson.command.DeadlineCommand;
import Johnson.command.DeleteCommand;
import Johnson.command.EventCommand;
import Johnson.command.ExitCommand;
import Johnson.command.FindCommand;
import Johnson.command.FindTagCommand;
import Johnson.command.GreetCommand;
import Johnson.command.ListCommand;
import Johnson.command.MarkCommand;
import Johnson.command.ToDoCommand;
import Johnson.command.UnmarkCommand;

import Johnson.exceptions.MissingDateException;
import Johnson.exceptions.MissingDividerException;
import Johnson.exceptions.MissingTaskException;
import Johnson.exceptions.UnknownCommandException;

import java.util.Arrays;
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
            Pattern.compile("(?<task>.+?)\\s*/\\s*(?<keyword>by|from|on)?\\s*(?<date>\\d{4}-\\d{2}-\\d{2})?\\s*(?<time>\\d{1,2}:\\d{2})?(?:\\s*/\\s*(?<tags>(#\\w+\\s*)*))?");

    /**
     * Pattern to match commands with an index.
     */
    private static final Pattern INDEXED_COMMAND_PATTERN =
            Pattern.compile("\\s*(?<index>\\d+)\\s*");

    /**
     * Pattern to match tags.
     */
    private static final Pattern TODO_PATTERN = Pattern.compile("(?<task>.+?)\\s*(?:/\\s*(?<tags>(#\\w+\\s*)*))?");

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

        if (Arrays.stream(GreetCommand.COMMAND_WORDS).anyMatch(greeting -> greeting.equals(command))) {
            parsedCommand = new GreetCommand();
            return parsedCommand;
        }
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
        case FindTagCommand.COMMAND_WORD:
            parsedCommand = new FindTagCommand(arguments.trim());
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

        Matcher deadlineMatcher = DATETIME_COMMAND_PATTERN.matcher(arguments);
        String task = null;
        String date = null;
        String time = null;
        String[] tags = null;
        if (deadlineMatcher.matches()) {
            task = deadlineMatcher.group("task");
            date = deadlineMatcher.group("date");
            time = deadlineMatcher.group("time");
            tags = prepTags(deadlineMatcher.group("tags"));

        } else {
            System.out.println("Unknown command in prep method");
            throw new UnknownCommandException("Unknown command");
        }

        if (task == null || task.trim().isEmpty()) {
            throw new MissingTaskException("missing a task");
        }
        if (date == null) {
            throw new MissingDateException("missing a date");
        }

        return new DeadlineCommand(task, date, time, tags);
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

        Matcher eventMatcher = DATETIME_COMMAND_PATTERN.matcher(arguments);
        String task = null;
        String date = null;
        String time = null;
        String[] tags = null;

        if (eventMatcher.matches()) {
            task = eventMatcher.group("task");
            date = eventMatcher.group("date");
            time = eventMatcher.group("time");
            tags = prepTags(eventMatcher.group("tags"));
        } else {
            System.out.println("Unknown command in prep method");
            throw new UnknownCommandException("Unknown command");
        }

        if (task == null || task.trim().isEmpty()) {
            throw new MissingTaskException("missing a task");
        }
        if (date == null) {
            throw new MissingDateException("missing a date");
        }
        return new EventCommand(task, date, time, tags);
    }

    /**
     * Prepares an indexed command from the given arguments.
     *
     * @param arguments the arguments for the indexed command.
     * @return the index parsed from the arguments.
     */
    private int prepIndexedCommand(String arguments) {
        Matcher indexMatcher = INDEXED_COMMAND_PATTERN.matcher(arguments);

        if (!indexMatcher.matches()) {
            return -1;
        }

        String index = indexMatcher.group("index").trim();

        if (index.isEmpty()) {
            return -1;
        }

        return Integer.parseInt(index);
    }

    /**
     * Prepares a ToDoCommand from the given arguments.
     *
     * @param arguments the arguments for the todo command.
     * @return the prepared ToDoCommand.
     * @throws MissingTaskException if the task is missing.
     */
    private Command prepToDo(String arguments) throws MissingTaskException {
        String task = null;
        String[] tags = null;

        Matcher todoMatcher = TODO_PATTERN.matcher(arguments);

        if (todoMatcher.matches()) {
            task = todoMatcher.group("task");
            tags = prepTags(todoMatcher.group("tags"));
        }

        if (task == null || task.trim().isEmpty()) {
            throw new MissingTaskException("missing a task");
        }

        if (tags == null) {
            return new ToDoCommand(task);
        }

        return new ToDoCommand(task, tags);
    }

    /**
     * Prepares tags from the given string.
     *
     * @param tags the string containing the tags.
     * @return the array of tags as strings.
     */
    private String[] prepTags(String tags) {
        if (tags == null) {
            return null;
        }
        return Arrays.stream(tags.trim().split("#"))
                    .map(String::trim)
                    .filter(tag -> !tag.isEmpty())
                    .toArray(String[]::new);
    }
}

