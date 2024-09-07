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
     * Parses the string input and returns a command that can be executed
     * to carry out its task.
     *
     * @param userInput full user input from the user.
     * @return command to be carried out.
     * @throws CowExceptions used to print any exception that might happen.
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
     * Prepares the recurring command.
     *
     * @param args the expected command arguments.
     * @return a recurring command or invalid command if unsuccessful.
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
     * Creates recurring command from the arguments.
     *
     * @param matcher matcher used for getting the arguments.
     * @param times to recur.
     * @return Reoccurring command or invalid command.
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
     * Gets the matcher for the recurring command.
     *
     * @param args from the command
     * @return the matcher used to extract arguments.
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
     * Prepares find command with arguments.
     *
     * @param arguments of task to find.
     * @return the corresponding command.
     */
    private static Command prepFindCommand(String arguments) {
        IncorrectCommand command = checkEmptyCommand(arguments, FindCommand.MESSAGE_USAGE);
        if (command != null) {
            return command;
        }
        return new FindCommand(arguments);
    }

    /**
     * Checks if the command arguments is empty.
     *
     * @param arguments from the regex.
     * @param usage the example usage of the command it is checking.
     * @return an IncorrectCommand with the example usage.
     */
    private static IncorrectCommand checkEmptyCommand(String arguments, String usage) {
        if (arguments == null || arguments.isEmpty()) {
            return getIncorrectCommand(usage);
        }
        return null;
    }

    /**
     * Creates the IncorrectCommand.
     *
     * @param usage the correct example usage.
     * @return the IncorrectCommand object.
     */
    private static IncorrectCommand getIncorrectCommand(String usage) {
        return new IncorrectCommand(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                usage));
    }

    /**
     * Parses the index used for marking, unmarking and delete commands.
     *
     * @param args provided by command.
     * @return the parse int.
     * @throws CowExceptions exceptions due to invalid index.
     */
    private static int getMarkUnmarkInt(String args) throws CowExceptions {
        try {
            return Integer.parseInt(args.trim());
        } catch (NumberFormatException e) {
            throw new CowExceptions(Messages.INDEX_INVALID);
        }
    }

    /**
     * Preps the argument to create the due command.
     *
     * @param args provided beside command.
     * @return a due command.
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
     * Preps the argument to create the deadline command.
     *
     * @param args provided beside command.
     * @return a deadline command.
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
     * Preps the argument to create the event command.
     *
     * @param args provided beside command.
     * @return an event command.
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
     * Checks if matcher matches.
     *
     * @param matcher the matcher to check.
     * @param usage the correct command usage example.
     * @return IncorrectCommand object.
     */
    private static IncorrectCommand checkMatcher(Matcher matcher, String usage) {
        if (!matcher.matches()) {
            return getIncorrectCommand(usage);
        }
        return null;
    }

    /**
     * Preps the argument to create the todo command.
     *
     * @param args provided beside command.
     * @return a todo command.
     */
    private static Command prepareTodo(String args) {
        if (args == null || args.isEmpty()) {
            return getIncorrectCommand(TodoCommand.MESSAGE_USAGE);
        }
        return new TodoCommand(args);
    }
}
