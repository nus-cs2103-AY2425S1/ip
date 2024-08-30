package snowy.parser;

import snowy.common.*;
import snowy.data.SnowyException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parses user commands.
 */
public class Parser {

    public static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");
    public static final Pattern TODO_ARGS_FORMAT = Pattern.compile("(?<description>.+)");
    public static final Pattern INDEX_ARGS_FORMAT = Pattern.compile("(?<taskNumber>\\d+)");
    public static final Pattern DEADLINE_ARGS_FORMAT = Pattern.compile("(?<description>.+) /by (?<date>.+)");
    public static final Pattern EVENT_ARGS_FORMAT = Pattern.compile("(?<description>.+) /from (?<from>.+) /to (?<to>.+)");

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

                default:
                    return new InvalidCommand("Sorry, I do not understand that command.");
            }
        } catch (SnowyException e) {
            return new InvalidCommand(e.getMessage());
        }
    }

    private Command parseTodoCommand(String arg) throws SnowyException {
        final Matcher matcher = TODO_ARGS_FORMAT.matcher(arg);

        if (!matcher.matches()) {
            throw new SnowyException("Invalid todo format");
        }

        return new TodoCommand(matcher.group("description"));
    }

    private Command parseDeadlineCommand(String arg) throws SnowyException {
        final Matcher matcher = DEADLINE_ARGS_FORMAT.matcher(arg);

        if (!matcher.matches()) {
            throw new SnowyException("Invalid deadline format");
        }

        String description = matcher.group("description");
        String date = matcher.group("date");
        return new DeadlineCommand(description, date);
    }

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

    private Command parseDeleteCommand(String arg) throws SnowyException {
        final Matcher matcher = INDEX_ARGS_FORMAT.matcher(arg);

        if (!matcher.matches()) {
            throw new SnowyException("Invalid task number format for delete");
        }

        int index = Integer.parseInt(matcher.group("taskNumber"));
        return new DeleteCommand(index);
    }

    private Command parseMarkCommand(String arg) throws SnowyException {
        final Matcher matcher = INDEX_ARGS_FORMAT.matcher(arg);

        if (!matcher.matches()) {
            throw new SnowyException("Invalid task number format for mark");
        }

        int index = Integer.parseInt(matcher.group("taskNumber"));
        return new MarkCommand(index);
    }

    private Command parseUnmarkCommand(String arg) throws SnowyException {
        final Matcher matcher = INDEX_ARGS_FORMAT.matcher(arg);

        if (!matcher.matches()) {
            throw new SnowyException("Invalid task number format for unmark");
        }

        int index = Integer.parseInt(matcher.group("taskNumber"));
        return new UnmarkCommand(index);
    }
}
