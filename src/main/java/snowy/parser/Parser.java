package snowy.parser;

import snowy.common.DeadlineCommand;
import snowy.common.DeleteCommand;
import snowy.common.EventCommand;
import snowy.common.ExitCommand;
import snowy.common.FindCommand;
import snowy.common.InvalidCommand;
import snowy.common.ListCommand;
import snowy.common.MarkCommand;
import snowy.common.TagCommand;
import snowy.common.TodoCommand;
import snowy.common.UnmarkCommand;
import snowy.common.Command;
import snowy.common.CommandResult;
import snowy.data.SnowyException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    public static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");
    public static final Pattern TODO_ARGS_FORMAT = Pattern.compile("(?<description>.+)");
    public static final Pattern INDEX_ARGS_FORMAT = Pattern.compile("(?<taskNumber>\\d+)");
    public static final Pattern DEADLINE_ARGS_FORMAT = Pattern.compile("(?<description>.+) /by (?<date>.+)");
    public static final Pattern EVENT_ARGS_FORMAT = Pattern.compile("(?<description>.+) /from (?<from>.+) /to (?<to>.+)");
    public static final Pattern FIND_ARGS_FORMAT = Pattern.compile("(?<keywords>\\S+(?:\\s+\\S+)*)");
    public static final Pattern TAG_ARGS_FORMAT = Pattern.compile("(?<taskNumber>\\d+)(?<tag>.+)");


    /**
     * Parses user input into a command for execution.
     *
     * @param input full user input string
     * @return the command based on the user input
     */
    public Command parseCommand(String input) {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(input.trim());

        if (!matcher.matches()) {
            return new InvalidCommand("Sorry, I do not understand that command.");
        }

        final String command = matcher.group("commandWord").toLowerCase();
        final String arg = matcher.group("arguments").trim();

        try {
            switch (command) {
            case "todo":
                return parseTodoCommand(arg);
            case "deadline":
                return parseDeadlineCommand(arg);
            case "event":
                return parseEventCommand(arg);
            case "delete":
                return parseDeleteCommand(arg);
            case "list":
                return new ListCommand();
            case "mark":
                return parseMarkCommand(arg);
            case "unmark":
                return parseUnmarkCommand(arg);
            case "bye":
                return new ExitCommand();
            case "find":
                return parseFindCommand(arg);
            case "tag":
                return parseTagCommand(arg);
            default:
                return new InvalidCommand("Sorry, I do not understand that command.");
            }
        } catch (SnowyException e) {
            return new InvalidCommand(e.getMessage());
        }
    }

    /**
     * Parses arguments and creates a TodoCommand.
     *
     * @param arg the arguments string containing the task description
     * @return the prepared TodoCommand
     * @throws SnowyException if the format of the arguments is invalid
     */
    private Command parseTodoCommand(String arg) throws SnowyException {
        final Matcher matcher = TODO_ARGS_FORMAT.matcher(arg);

        if (!matcher.matches()) {
            throw new SnowyException("Invalid todo format");
        }

        return new TodoCommand(matcher.group("description"));
    }

    /**
     * Parses arguments and creates a DeadlineCommand.
     *
     * @param arg the arguments string containing the task description and date
     * @return the prepared DeadlineCommand
     * @throws SnowyException if the format of the arguments is invalid
     */
    private Command parseDeadlineCommand(String arg) throws SnowyException {
        final Matcher matcher = DEADLINE_ARGS_FORMAT.matcher(arg);

        if (!matcher.matches()) {
            throw new SnowyException("Invalid deadline format");
        }

        String description = matcher.group("description");
        String date = matcher.group("date");
        return new DeadlineCommand(description, date);
    }

    /**
     * Parses arguments and creates an EventCommand.
     *
     * @param arg the arguments string containing the task description, start time, and end time
     * @return the prepared EventCommand
     * @throws SnowyException if the format of the arguments is invalid
     */
    private Command parseEventCommand(String arg) throws SnowyException {
        final Matcher matcher = EVENT_ARGS_FORMAT.matcher(arg);

        if (!matcher.matches()) {
            throw new SnowyException("Invalid event format");
        }

        String description = matcher.group("description");
        String from = matcher.group("from");
        String to = matcher.group("to");
        return new EventCommand(description, from, to);
    }

    /**
     * Parses arguments and creates a DeleteCommand.
     *
     * @param arg the arguments string containing the task number
     * @return the prepared DeleteCommand
     * @throws SnowyException if the format of the arguments is invalid
     */
    private Command parseDeleteCommand(String arg) throws SnowyException {
        final Matcher matcher = INDEX_ARGS_FORMAT.matcher(arg);

        if (!matcher.matches()) {
            throw new SnowyException("Invalid task number format for delete");
        }

        int index = Integer.parseInt(matcher.group("taskNumber"));
        return new DeleteCommand(index);
    }

    /**
     * Parses arguments and creates a MarkCommand.
     *
     * @param arg the arguments string containing the task number
     * @return the prepared MarkCommand
     * @throws SnowyException if the format of the arguments is invalid
     */
    private Command parseMarkCommand(String arg) throws SnowyException {
        final Matcher matcher = INDEX_ARGS_FORMAT.matcher(arg);

        if (!matcher.matches()) {
            throw new SnowyException("Invalid task number format for mark");
        }

        int index = Integer.parseInt(matcher.group("taskNumber"));
        return new MarkCommand(index);
    }

    /**
     * Parses arguments and creates an UnmarkCommand.
     *
     * @param arg the arguments string containing the task number
     * @return the prepared UnmarkCommand
     * @throws SnowyException if the format of the arguments is invalid
     */
    private Command parseUnmarkCommand(String arg) throws SnowyException {
        final Matcher matcher = INDEX_ARGS_FORMAT.matcher(arg);

        if (!matcher.matches()) {
            throw new SnowyException("Invalid task number format for unmark");
        }

        int index = Integer.parseInt(matcher.group("taskNumber"));
        return new UnmarkCommand(index);
    }

    private Command parseFindCommand(String arg) throws SnowyException {
        final Matcher matcher = FIND_ARGS_FORMAT.matcher(arg.trim());

        if (!matcher.matches()) {
            throw new SnowyException("The find command requires at least one keyword.");
        }

        String keywords = matcher.group("keywords").trim();
        return new FindCommand(keywords);
    }

    private Command parseTagCommand(String arg) throws SnowyException {
        final Matcher matcher = TAG_ARGS_FORMAT.matcher(arg.trim());

        if (!matcher.matches()) {
            throw new SnowyException("The tag command requires index and tag name.");
        }

        int index = Integer.parseInt(matcher.group("taskNumber"));
        String tag = matcher.group("tag");

        return new TagCommand(index, tag);
    }

}
