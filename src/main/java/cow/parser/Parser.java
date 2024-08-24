package cow.parser;

import cow.commands.*;
import cow.exceptions.CowExceptions;
import cow.message.Messages;
import cow.tasks.Deadlines;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// solution below inspired by https://github.com/se-edu/addressbook-level2/tree/master
public class Parser {
    public static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses the string input and returns a command that can be executed
     * to carry out its' task
     * @param userInput full user input from the user
     * @return command to be carried out
     * @throws CowExceptions used to print any exception that might happen
     */
    public static Command parse(String userInput) throws CowExceptions {
        Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());

        if (!matcher.matches()) {
            return new IncorrectCommand(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT
                    , HelpCommand.MESSAGE_USAGE));
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
            default:
                return new HelpCommand();
        }
    }

    /**
     * parses the index used for marking, unmarking and delete commands
     * @param args provided by command
     * @return the parse int
     * @throws CowExceptions exceptions due to invalid index
     */
    private static int getMarkUnmarkInt(String args) throws CowExceptions {
        try {
            return Integer.parseInt(args.trim());
        } catch (NumberFormatException e) {
            throw new CowExceptions(Messages.INDEX_INVALID);
        }
    }

    /**
     * Preps the argument to create the due command
     * @param args provided beside command
     * @return a due command
     */
    private static Command prepDue(String args) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        try {
            LocalDate parsedDate = LocalDate.parse(args.trim(), formatter);
            return new DueCommand(parsedDate);
        } catch (DateTimeParseException e) {
            return new IncorrectCommand(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT
                    , DueCommand.MESSAGE_USAGE));
        }
    }

    /**
     * Preps the argument to create the deadline command
     * @param args provided beside command
     * @return a deadline command
     */
    private static Command prepareDeadline(String args) {
        Pattern pattern = Pattern.compile("^(?<desc>.+?)\\s*/by\\s*(?<by>.*)$");
        Matcher matcher = pattern.matcher(args.trim());
        if (!matcher.matches()) {
            return new IncorrectCommand(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT
                    , DeadlineCommand.MESSAGE_USAGE));
        }

        try {
            return new DeadlineCommand(matcher.group("desc"),
                    LocalDateTime.parse(matcher.group("by"), Deadlines.FORMAT));
        } catch (DateTimeParseException e) {
            return new IncorrectCommand(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT
                    , DeadlineCommand.MESSAGE_USAGE));
        }
    }

    /**
     * Preps the argument to create the event command
     * @param args provided beside command
     * @return an event command
     */
    private static Command prepareEvent(String args) {
        Pattern pattern = Pattern.compile("^(?<desc>.+?)\\s*/from\\s*(?<from>.+?)\\s*/to\\s*(?<to>.+)$");
        Matcher matcher = pattern.matcher(args.trim());
        if (!matcher.matches()) {
            return new IncorrectCommand(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT
                    , EventCommand.MESSAGE_USAGE));
        }

        return new EventCommand(matcher.group("desc"),
                matcher.group("from"), matcher.group("to"));
    }

    /**
     * Preps the argument to create the todo command
     * @param args provided beside command
     * @return a todo command
     */
    private static Command prepareTodo(String args) {
        if (args == null || args.isEmpty()) {
            return new IncorrectCommand(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT
                    , TodoCommand.MESSAGE_USAGE));
        }
        return new TodoCommand(args);
    }
}
