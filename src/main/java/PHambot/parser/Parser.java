package PHambot.parser;

import PHambot.command.Command;
import PHambot.command.DeadlineCommand;
import PHambot.command.DeleteCommand;
import PHambot.command.EventCommand;
import PHambot.command.ExitCommand;
import PHambot.command.ListCommand;
import PHambot.command.MarkCommand;
import PHambot.command.ToDoCommand;
import PHambot.command.UnmarkCommand;

import PHambot.exceptions.MissingTaskException;
import PHambot.exceptions.MissingDividerException;
import PHambot.exceptions.MissingDateException;

import PHambot.utils.Utilities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

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
     *
     */
    public Command parseCommand(String userInput) {
        Matcher matcher = BASIC_COMMAND_PATTERN.matcher(userInput.trim());

        if (!matcher.matches()) {
            //Invalid command exception
            return null;
        }
        String command = matcher.group("command");
        String arguments = matcher.group("arguments");

        try {
            return switch (command) {
                case ToDoCommand.COMMAND_WORD -> prepToDo(arguments);
                case ListCommand.COMMAND_WORD -> new ListCommand();
                case DeadlineCommand.COMMAND_WORD -> prepDeadline(arguments);
                case MarkCommand.COMMAND_WORD -> new MarkCommand(prepIndexedCommand(arguments));
                case UnmarkCommand.COMMAND_WORD -> new UnmarkCommand(prepIndexedCommand(arguments));
                case EventCommand.COMMAND_WORD -> prepEvent(arguments);
                case ExitCommand.COMMAND_WORD -> new ExitCommand();
                case DeleteCommand.COMMAND_WORD -> new DeleteCommand(prepIndexedCommand(arguments));
                default ->
                    //placeholder for future; should default to help
                        null;
            };
        } catch (MissingTaskException e) {
            Utilities.OutlineMessage("Missing a task");
        } catch (MissingDividerException e) {
            Utilities.OutlineMessage("Missing a divider");
        } catch (MissingDateException e) {
            Utilities.OutlineMessage("Missing a date");
        }
        return null;
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
            MissingDividerException, MissingTaskException, MissingDateException{
        Matcher deadlineMatcher = DATETIME_COMMAND_PATTERN.matcher(arguments);
        if (!arguments.contains("/")) {
            throw new MissingDividerException("missing a divider");
        }
        if (deadlineMatcher.matches()) {
            String task = deadlineMatcher.group("task");
            String date = deadlineMatcher.group("date");
            String time = deadlineMatcher.group("time");
            if (task == null) {
                throw new MissingTaskException("missing a task");
            }
            if (date == null) {
                throw new MissingDateException("missing a date");
            }
            return new DeadlineCommand(task, date, time);
        } else {
            deadlineMatcher = DATE_COMMAND_PATTERN.matcher(arguments);
            if (deadlineMatcher.matches()) {
                String task = deadlineMatcher.group("task");
                String date = deadlineMatcher.group("date");
                if (task == null) {
                    throw new MissingTaskException("missing a task");
                }
                if (date == null) {
                    throw new MissingDateException("missing a date");
                }
                return new DeadlineCommand(task, date);
            } else {
                return null;
            }
        }
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
            MissingTaskException, MissingDividerException, MissingDateException{
        Matcher eventMatcher = DATETIME_COMMAND_PATTERN.matcher(arguments);
        if (!arguments.contains("/")) {
            throw new MissingDividerException("missing a divider");
        }
        if (eventMatcher.matches()) {
            String task = eventMatcher.group("task");
            String date = eventMatcher.group("date");
            String time = eventMatcher.group("time");
            if (task == null) {
                throw new MissingTaskException("missing a task");
            }
            if (date == null) {
                throw new MissingDateException("missing a date");
            }
            return new EventCommand(task, date, time);
        } else {
            eventMatcher = DATE_COMMAND_PATTERN.matcher(arguments);
            if (eventMatcher.matches()) {
                String task = eventMatcher.group("task");
                String date = eventMatcher.group("date");
                if (task == null) {
                    throw new MissingTaskException("missing a task");
                }
                if (date == null) {
                    throw new MissingDateException("missing a date");
                }
                return new EventCommand(task, date);
            } else {
                //throw exception
                return null;
            }
        }
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
            //throw invalid format exception
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
