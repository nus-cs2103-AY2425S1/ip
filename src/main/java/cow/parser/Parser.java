package cow.parser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cow.commands.ByeCommand;
import cow.commands.Command;
import cow.commands.DeadlineCommand;
import cow.commands.DeleteCommand;
import cow.commands.DueCommand;
import cow.commands.EventCommand;
import cow.commands.FindCommand;
import cow.commands.HelpCommand;
import cow.commands.IncorrectCommand;
import cow.commands.ListCommand;
import cow.commands.MarkCommand;
import cow.commands.RecurringCommand;
import cow.commands.TodoCommand;
import cow.commands.UnmarkCommand;
import cow.exceptions.CowExceptions;
import cow.message.Messages;
import cow.tasks.Deadlines;

// solution below inspired by https://github.com/se-edu/addressbook-level2/tree/master

/**
 * Class to handle the parsing of input commands.
 **/
@SuppressWarnings("checkstyle:Regexp")
public class Parser {
    public static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses the user input and returns the corresponding command.
     *
     * @param userInput The input provided by the user.
     * @return The command corresponding to the user input.
     * @throws CowExceptions If there is an error in parsing the command.
     */
    public static Command parse(String userInput) throws CowExceptions {
        Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        IncorrectCommand isNotMatch = checkMatcher(matcher, HelpCommand.MESSAGE_USAGE);
        if (isNotMatch != null) {
            return isNotMatch;
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        switch (commandWord) {
        case ListCommand.COMMAND_WORD:
            return new ListCommand();
        case ByeCommand.COMMAND_WORD:
            return new ByeCommand();
        case TodoCommand.COMMAND_WORD:
            return prepareTodo(arguments);
        case DeadlineCommand.COMMAND_WORD:
            return prepareDeadline(arguments);
        case EventCommand.COMMAND_WORD:
            return prepareEvent(arguments);
        case MarkCommand.COMMAND_WORD:
            return new MarkCommand(getMarkUnmarkInt(arguments));
        case UnmarkCommand.COMMAND_WORD:
            return new UnmarkCommand(getMarkUnmarkInt(arguments));
        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommand(getMarkUnmarkInt(arguments));
        case DueCommand.COMMAND_WORD:
            return prepDue(arguments);
        case FindCommand.COMMAND_WORD:
            return prepFindCommand(arguments);
        case RecurringCommand.COMMAND_WORD:
            return prepRecurringCommand(arguments);
        default:
            return new HelpCommand();
        }
    }

    /**
     * Prepares a recurring command with the provided arguments.
     *
     * @param args The arguments for the recurring command.
     * @return The corresponding recurring command or an incorrect command if the arguments are invalid.
     */
    private static Command prepRecurringCommand(String args) {
        Matcher matcher = getRecurringMatcher(args);
        IncorrectCommand isNotMatch = checkMatcher(matcher, RecurringCommand.MESSAGE_USAGE);
        if (isNotMatch != null) {
            return isNotMatch;
        }
        int times;
        try {
            times = Integer.parseInt(matcher.group("times").trim());
        } catch (NumberFormatException e) {
            return getIncorrectCommand(RecurringCommand.MESSAGE_USAGE);
        }
        return getRecurringCommand(matcher, times);
    }

    /**
     * Creates a RecurringCommand with the provided matcher and times.
     *
     * @param matcher The matcher containing the command arguments.
     * @param times   The number of times the task recurs.
     * @return The corresponding RecurringCommand or an IncorrectCommand if the arguments are invalid.
     */
    private static Command getRecurringCommand(Matcher matcher, int times) {
        try {
            return new RecurringCommand(matcher.group("desc").trim(),
                    LocalDateTime.parse(matcher.group("start").trim(), Deadlines.FORMAT),
                    matcher.group("freq").trim(),
                    times);
        } catch (DateTimeParseException e) {
            return getIncorrectCommand(RecurringCommand.MESSAGE_USAGE);
        }
    }

    /**
     * Returns a Matcher for the recurring command arguments.
     *
     * @param args The arguments for the recurring command.
     * @return A Matcher for the recurring command arguments.
     */
    private static Matcher getRecurringMatcher(String args) {
        String regex = "^(?<desc>.+?)\\s"
                + "*/start\\s*(?<start>.+?)\\s"
                + "*/freq\\s*(?<freq>.+?)\\s"
                + "*/times\\s*(?<times>.+?)$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(args.trim());
    }

    /**
     * Prepares a find command with the provided arguments.
     *
     * @param arguments The arguments for the find command.
     * @return The corresponding find command or an incorrect command if the arguments are invalid.
     */
    private static Command prepFindCommand(String arguments) {
        IncorrectCommand command = checkEmptyCommand(arguments, FindCommand.MESSAGE_USAGE);
        if (command != null) {
            return command;
        }
        return new FindCommand(arguments);
    }

    /**
     * Checks if the provided arguments are empty and returns an IncorrectCommand if they are.
     *
     * @param arguments The arguments to check.
     * @param usage     The correct usage message to include in the IncorrectCommand.
     * @return An IncorrectCommand if the arguments are empty, otherwise null.
     */
    private static IncorrectCommand checkEmptyCommand(String arguments, String usage) {
        if (arguments == null || arguments.isEmpty()) {
            return getIncorrectCommand(usage);
        }
        return null;
    }

    /**
     * Returns an IncorrectCommand with the provided usage message.
     *
     * @param usage The correct usage message to include in the IncorrectCommand.
     * @return An IncorrectCommand with the provided usage message.
     */
    private static IncorrectCommand getIncorrectCommand(String usage) {
        return new IncorrectCommand(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                usage));
    }

    /**
     * Parses the argument to get the index for mark/unmark commands.
     *
     * @param args The argument containing the index.
     * @return The parsed index.
     * @throws CowExceptions If the argument is not a valid integer.
     */
    private static int getMarkUnmarkInt(String args) throws CowExceptions {
        try {
            return Integer.parseInt(args.trim());
        } catch (NumberFormatException e) {
            throw new CowExceptions(Messages.INDEX_INVALID);
        }
    }

    /**
     * Prepares a due command with the provided arguments.
     *
     * @param args The arguments for the due command.
     * @return The corresponding due command or an incorrect command if the arguments are invalid.
     */
    private static Command prepDue(String args) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        try {
            LocalDate parsedDate = LocalDate.parse(args.trim(), formatter);
            return new DueCommand(parsedDate);
        } catch (DateTimeParseException e) {
            return getIncorrectCommand(DueCommand.MESSAGE_USAGE);
        }
    }

    /**
     * Prepares a deadline command with the provided arguments.
     *
     * @param args The arguments for the deadline command.
     * @return The corresponding deadline command or an incorrect command if the arguments are invalid.
     */
    private static Command prepareDeadline(String args) {
        Pattern pattern = Pattern.compile("^(?<desc>.+?)\\s*/by\\s*(?<by>.*)$");
        Matcher matcher = pattern.matcher(args.trim());
        IncorrectCommand isNotMatch = checkMatcher(matcher, DeadlineCommand.MESSAGE_USAGE);
        if (isNotMatch != null) {
            return isNotMatch;
        }
        try {
            return new DeadlineCommand(matcher.group("desc"),
                    LocalDateTime.parse(matcher.group("by"), Deadlines.FORMAT));
        } catch (DateTimeParseException e) {
            return getIncorrectCommand(DeadlineCommand.MESSAGE_USAGE);
        }
    }

    /**
     * Prepares an event command with the provided arguments.
     *
     * @param args The arguments for the event command.
     * @return The corresponding event command or an incorrect command if the arguments are invalid.
     */
    private static Command prepareEvent(String args) {
        Pattern pattern = Pattern.compile("^(?<desc>.+?)\\s*/from\\s*(?<from>.+?)\\s*/to\\s*(?<to>.+)$");
        Matcher matcher = pattern.matcher(args.trim());
        IncorrectCommand isNotMatch = checkMatcher(matcher, EventCommand.MESSAGE_USAGE);
        if (isNotMatch != null) {
            return isNotMatch;
        }
        return new EventCommand(matcher.group("desc"),
                matcher.group("from"), matcher.group("to"));
    }

    /**
     * Checks if the matcher matches the pattern and returns an IncorrectCommand if it does not.
     *
     * @param matcher The matcher to check.
     * @param usage   The correct usage message to include in the IncorrectCommand.
     * @return An IncorrectCommand if the matcher does not match, otherwise null.
     */
    private static IncorrectCommand checkMatcher(Matcher matcher, String usage) {
        if (!matcher.matches()) {
            return getIncorrectCommand(usage);
        }
        return null;
    }

    /**
     * Prepares a todo command with the provided arguments.
     *
     * @param args The arguments for the todo command.
     * @return The corresponding todo command or an incorrect command if the arguments are invalid.
     */
    private static Command prepareTodo(String args) {
        if (args == null || args.isEmpty()) {
            return getIncorrectCommand(TodoCommand.MESSAGE_USAGE);
        }
        return new TodoCommand(args);
    }
}
