package denim;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import denim.commands.Command;
import denim.commands.DeadlineCommand;
import denim.commands.DeleteCommand;
import denim.commands.EventCommand;
import denim.commands.ExitCommand;
import denim.commands.FindCommand;
import denim.commands.HelpCommand;
import denim.commands.InvalidCommand;
import denim.commands.ListCommand;
import denim.commands.MarkCommand;
import denim.commands.TodoCommand;
import denim.commands.UnmarkCommand;

/**
 * Parses user input into executable commands.
 * This class interprets the input from the user and returns
 * the corresponding command to be executed.
 */
public class Parser {

    public static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    public static final Pattern TODO_ARGUMENT_FORMAT = Pattern.compile("(?<taskDescription>.+)");

    public static final Pattern EVENT_ARGUMENT_FORMAT = Pattern.compile("(?<taskDescription>.+) /from "
            + "(?<startTime>\\d{2}/\\d{2}/\\d{4} \\d{4}) /to (?<endTime>\\d{2}/\\d{2}/\\d{4} \\d{4})");

    public static final Pattern DEADLINE_ARGUMENT_FORMAT = Pattern.compile("(?<taskDescription>.+) /by "
            + "(?<dateTime>\\d{2}/\\d{2}/\\d{4} \\d{4})");

    public static final Pattern MARK_UNMARK_DELETE_ARGUMENT_FORMAT = Pattern.compile("(?<taskNumber>\\d+)");

    public static final Pattern FIND_ARGUMENT_FORMAT = Pattern.compile("(?<taskDescription>.+)");

    /**
     * Parses the user input and returns the corresponding command.
     *
     * @param userInput The user input string.
     * @return The command corresponding to the user input.
     */
    public Command parseCommand(String userInput) {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());

        if (!matcher.matches()) {
            return new InvalidCommand("Unknown Command", "Command for help: help");
        }

        final String commandWord = matcher.group("commandWord").trim();
        final String arguments = matcher.group("arguments").trim();

        switch (commandWord) {
        case TodoCommand.COMMAND_WORD:
            return prepareTodo(arguments);

        case EventCommand.COMMAND_WORD:
            return prepareEvent(arguments);

        case DeadlineCommand.COMMAND_WORD:
            return prepareDeadline(arguments);

        case MarkCommand.COMMAND_WORD:
            return prepareMark(arguments);

        case UnmarkCommand.COMMAND_WORD:
            return prepareUnmark(arguments);

        case DeleteCommand.COMMAND_WORD:
            return prepareDelete(arguments);

        case ListCommand.COMMAND_WORD:
            return prepareList();

        case ExitCommand.COMMAND_WORD:
            return prepareBye();

        case HelpCommand.COMMAND_WORD:
            return prepareHelp();

        case FindCommand.COMMAND_WORD:
            return prepareFind(arguments);

        default:
            return new InvalidCommand("Invalid Command", "Command for help: help");
        }
    }

    /**
     * Prepares the command for a deadline task.
     *
     * @param args The arguments for the deadline command.
     * @return The deadline command or an invalid command if the arguments are incorrect.
     */
    private Command prepareDeadline(String args) {
        final Matcher matcher = DEADLINE_ARGUMENT_FORMAT.matcher(args);

        if (!matcher.matches()) {
            return new InvalidCommand("Wrong format for deadline command", DeadlineCommand.COMMAND_USAGE);
        }

        String dateTime = matcher.group("dateTime");
        DateTimeFormatter deadlineFormatter = DateTimeFormatter.ofPattern("dd/MM/yyy HHmm");

        if (!isValidMonthOfYear(deadlineFormatter, dateTime)) {
            return new InvalidCommand("Invalid Date Time Format", "Format Accepted: dd/MM/yyyy HHmm");
        }

        String taskDescription = matcher.group("taskDescription");
        LocalDateTime deadline = LocalDateTime.parse(dateTime, deadlineFormatter);
        return new DeadlineCommand(taskDescription, deadline);
    }

    /**
     * Prepares the command for an event task.
     *
     * @param args The arguments for the event command.
     * @return The event command or an invalid command if the arguments are incorrect.
     */
    private Command prepareEvent(String args) {
        final Matcher matcher = EVENT_ARGUMENT_FORMAT.matcher(args);

        if (!matcher.matches()) {
            return new InvalidCommand("Wrong format for event command", EventCommand.COMMAND_USAGE);
        }

        DateTimeFormatter eventFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");


        String taskDescription = matcher.group("taskDescription");
        String startTime = matcher.group("startTime");
        String endTime = matcher.group("endTime");

        if (!isValidMonthOfYear(eventFormatter, startTime) && !isValidMonthOfYear(eventFormatter, endTime)) {
            return new InvalidCommand("Invalid Date Time Format", "Format Accepted: dd/MM/yyyy HHmm");
        }

        if (!isValidEventDuration(eventFormatter, startTime, endTime)) {
            return new InvalidCommand("Event End Time is Earlier than or Equal to Event Start Time.",
                    "Format Accepted: event <description> /from <START TIME> /to <END TIME>");
        }

        LocalDateTime startEvent = LocalDateTime.parse(startTime, eventFormatter);
        LocalDateTime endEvent = LocalDateTime.parse(endTime, eventFormatter);
        return new EventCommand(taskDescription, startEvent, endEvent);
    }

    /**
     * Prepares the command for a todo task.
     *
     * @param args The arguments for the todo command.
     * @return The todo command or an invalid command if the arguments are incorrect.
     */
    private Command prepareTodo(String args) {
        final Matcher matcher = TODO_ARGUMENT_FORMAT.matcher(args);

        if (!matcher.matches()) {
            return new InvalidCommand("Wrong format for todo command", TodoCommand.COMMAND_USAGE);
        }
        String taskDescription = matcher.group("taskDescription");
        return new TodoCommand(taskDescription);
    }

    /**
     * Prepares the command to mark a task as completed.
     *
     * @param args The arguments for the mark command.
     * @return The mark command or an invalid command if the arguments are incorrect.
     */
    private Command prepareMark(String args) {
        final Matcher matcher = MARK_UNMARK_DELETE_ARGUMENT_FORMAT.matcher(args);

        if (!matcher.matches()) {
            return new InvalidCommand("Wrong format for mark command", MarkCommand.COMMAND_USAGE);
        }
        int index = Integer.parseInt(matcher.group("taskNumber"));
        return new MarkCommand(index - Denim.INDEX_OFFSET);
    }

    /**
     * Prepares the command to unmark a task as incomplete.
     *
     * @param args The arguments for the unmark command.
     * @return The unmark command or an invalid command if the arguments are incorrect.
     */
    private Command prepareUnmark(String args) {
        final Matcher matcher = MARK_UNMARK_DELETE_ARGUMENT_FORMAT.matcher(args);

        if (!matcher.matches()) {
            return new InvalidCommand("Wrong format for unmark command", UnmarkCommand.COMMAND_USAGE);
        }
        int index = Integer.parseInt(matcher.group("taskNumber"));
        return new UnmarkCommand(index - Denim.INDEX_OFFSET);
    }

    /**
     * Prepares the command to list all tasks.
     *
     * @return The list command.
     */
    private Command prepareList() {
        return new ListCommand();
    }

    /**
     * Prepares the command to delete a task.
     *
     * @param args The arguments for the delete command.
     * @return The delete command or an invalid command if the arguments are incorrect.
     */
    private Command prepareDelete(String args) {
        final Matcher matcher = MARK_UNMARK_DELETE_ARGUMENT_FORMAT.matcher(args);

        if (!matcher.matches()) {
            return new InvalidCommand("Wrong format for delete command", DeleteCommand.COMMAND_USAGE);
        }
        int index = Integer.parseInt(matcher.group("taskNumber"));
        return new DeleteCommand(index - Denim.INDEX_OFFSET);
    }

    /**
     * Prepares the command to exit the application.
     *
     * @return The exit command.
     */
    private Command prepareBye() {
        return new ExitCommand();
    }

    /**
     * Prepares the help command.
     *
     * @return The help command.
     */
    private Command prepareHelp() {
        return new HelpCommand();
    }

    private Command prepareFind(String args) {
        final Matcher matcher = FIND_ARGUMENT_FORMAT.matcher(args);

        if (!matcher.matches()) {
            return new InvalidCommand("Wrong format for find command", FindCommand.USAGE);
        }

        String[] keywords = args.split(" ");
        return new FindCommand(keywords);
    }

    /**
     * Checks if the provided date and time string represents a valid month of the year.
     *
     * @param formatter The date time formatter.
     * @param args      The date time string.
     * @return True if the date and time string is valid, false otherwise.
     */
    private boolean isValidMonthOfYear(DateTimeFormatter formatter, String args) {
        try {
            LocalDateTime.parse(args, formatter);
        } catch (DateTimeException e) {
            return false;
        }
        return true;
    }

    private boolean isValidEventDuration(DateTimeFormatter formatter, String from, String to) {
        try {
            LocalDateTime.parse(from, formatter);
            LocalDateTime.parse(to, formatter);
        } catch (DateTimeException e) {
            return false;
        }

        LocalDateTime eventFrom = LocalDateTime.parse(from, formatter);
        LocalDateTime eventTo = LocalDateTime.parse(from, formatter);

        if (eventFrom.isAfter(eventTo) || eventFrom.isEqual(eventTo)) {
            return false;
        }
        return true;
    }
}
