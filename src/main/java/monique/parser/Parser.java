package monique.parser;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Set;

import monique.command.AddCommand;
import monique.command.ByeCommand;
import monique.command.Command;
import monique.command.DeleteCommand;
import monique.command.FindCommand;
import monique.command.GuideCommand;
import monique.command.ListCommand;
import monique.command.MarkCommand;
import monique.command.UnknownCommand;
import monique.command.UnmarkCommand;
import monique.exception.IllegalDateFormatException;
import monique.exception.ParseException;
import monique.exception.UnknownCommandException;
import monique.task.Deadline;
import monique.task.Event;
import monique.task.Task;
import monique.task.ToDo;

/**
 * The <code>Parser</code> class processes user input and returns the corresponding <code>Command</code> object.
 * It handles various commands and task types, parsing the input to create appropriate command objects.
 */
public class Parser {
    public static final String NUMBER_FORMAT_ERROR_MESSAGE = "you have tried to use an invalid number";
    public static final String MISSING_ARGUMENT_ERROR_MESSAGE = "you have not provided any arguments";
    public static final String MISSING_DESCRIPTION_ERROR_MESSAGE = "you have not provided any description";
    public static final String MISSING_SEARCH_KEYS_ERROR_MESSAGE = "you have not provided any search keys";
    public static final String INVALID_DEADLINE_FORMAT_ERROR_MESSAGE = "invalid format for Deadline command";
    public static final String INVALID_EVENT_FORMAT_ERROR_MESSAGE = "invalid format for Event command";
    public static final String UNEXPECTED_VALUE_ERROR_MESSAGE = "Unexpected value: ";
    private static final Set<String> commands = Set.of("list", "mark", "unmark", "bye", "/commands", "delete", "find");
    private static final Set<String> taskTypes = Set.of("todo", "deadline", "event");
    private static final int INDEX_OFFSET = 1;
    /**
     * Parses the given command string and returns the corresponding <code>Command</code> object.
     * The method identifies the command type and creates the appropriate command object with the provided parameters.
     *
     * @param fullCommand The full command string to be parsed.
     * @return The <code>Command</code> object corresponding to the parsed command.
     */
    public static Command parse(String fullCommand) {
        String firstWord = fullCommand.split(" ")[0];
        boolean hasSecondWord = fullCommand.split(" ").length > 1;

        try {
            if (commands.contains(firstWord)) {
                return parseSystemCommand(firstWord, fullCommand, hasSecondWord);
            } else if (taskTypes.contains(firstWord)) {
                return parseTaskCommand(firstWord, fullCommand, hasSecondWord);
            } else {
                throw new UnknownCommandException(UNEXPECTED_VALUE_ERROR_MESSAGE + firstWord);
            }
        } catch (ParseException | IllegalDateFormatException | UnknownCommandException e) {
            return new UnknownCommand(e);
        }
    }
    private static Command parseSystemCommand(String firstWord,
                                              String fullCommand, boolean hasSecondWord)
            throws ParseException, UnknownCommandException {
        switch (firstWord) {
        case "bye":
            return new ByeCommand();
        case "list":
            return new ListCommand();
        case "mark":
            return parseMarkCommand(fullCommand, hasSecondWord);
        case "unmark":
            return parseUnmarkCommand(fullCommand, hasSecondWord);
        case "/commands":
            return new GuideCommand();
        case "delete":
            return parseDeleteCommand(fullCommand, hasSecondWord);
        case "find":
            return parseFindCommand(fullCommand, hasSecondWord);
        default:
            throw new UnknownCommandException(UNEXPECTED_VALUE_ERROR_MESSAGE + firstWord);
        }
    }

    private static Command parseMarkCommand(String fullCommand, boolean hasSecondWord) throws ParseException {
        if (!hasSecondWord) {
            throw new ParseException(MISSING_ARGUMENT_ERROR_MESSAGE);
        }
        try {
            int taskNum = Integer.parseInt(fullCommand.split("mark ")[1]) - INDEX_OFFSET;
            return new MarkCommand(taskNum);
        } catch (NumberFormatException nfe) {
            throw new ParseException(NUMBER_FORMAT_ERROR_MESSAGE);
        }
    }
    private static Command parseUnmarkCommand(String fullCommand, boolean hasSecondWord) throws ParseException {
        if (!hasSecondWord) {
            throw new ParseException(MISSING_ARGUMENT_ERROR_MESSAGE);
        }
        try {
            int taskNum = Integer.parseInt(fullCommand.split("unmark ")[1]) - INDEX_OFFSET;
            return new UnmarkCommand(taskNum);
        } catch (NumberFormatException nfe) {
            throw new ParseException(NUMBER_FORMAT_ERROR_MESSAGE);
        }
    }

    private static Command parseDeleteCommand(String fullCommand, boolean hasSecondWord) throws ParseException {
        if (!hasSecondWord) {
            throw new ParseException(MISSING_ARGUMENT_ERROR_MESSAGE);
        }
        try {
            int taskNum = Integer.parseInt(fullCommand.split("delete ")[1]) - INDEX_OFFSET;
            return new DeleteCommand(taskNum);
        } catch (NumberFormatException nfe) {
            throw new ParseException(NUMBER_FORMAT_ERROR_MESSAGE);
        }
    }

    private static Command parseFindCommand(String fullCommand, boolean hasSecondWord) throws ParseException {
        if (!hasSecondWord) {
            throw new ParseException(MISSING_SEARCH_KEYS_ERROR_MESSAGE);
        }
        String[] searchKeys = fullCommand.split("find ")[1].split(" ");
        return new FindCommand(searchKeys);
    }
    private static Command parseTaskCommand(String firstWord,
                                            String fullCommand, boolean hasSecondWord)
            throws ParseException, IllegalDateFormatException, UnknownCommandException {
        switch (firstWord) {
        case "todo":
            return parseTodoCommand(fullCommand, hasSecondWord);
        case "deadline":
            return parseDeadlineCommand(fullCommand);
        case "event":
            return parseEventCommand(fullCommand);
        default:
            throw new UnknownCommandException(UNEXPECTED_VALUE_ERROR_MESSAGE + firstWord);
        }
    }

    private static Command parseTodoCommand(String fullCommand, boolean hasSecondWord) throws ParseException {
        if (!hasSecondWord) {
            throw new ParseException(MISSING_DESCRIPTION_ERROR_MESSAGE);
        }
        String description = String.join(" ", Arrays.copyOfRange(fullCommand.split(" "),
                1, fullCommand.split(" ").length));
        Task taskToAdd = new ToDo(description);
        return new AddCommand(taskToAdd);
    }

    private static Command parseDeadlineCommand(String fullCommand) throws ParseException, IllegalDateFormatException {
        String[] parts = fullCommand.split("/by");
        if (parts.length <= 1) {
            throw new ParseException(INVALID_DEADLINE_FORMAT_ERROR_MESSAGE);
        }
        String byString = parts[1].trim();
        LocalDateTime by = DateParser.getDateTimeString(byString);
        String[] commandAndDescription = parts[0].trim().split(" ", 2);
        String description = commandAndDescription.length > 1 ? commandAndDescription[1] : "";
        Task taskToAdd = new Deadline(description, false, by, DateParser.hasTime(byString));
        return new AddCommand(taskToAdd);
    }

    private static Command parseEventCommand(String fullCommand) throws ParseException, IllegalDateFormatException {
        String[] fromSplit = fullCommand.split("/from");
        if (fromSplit.length != 2) {
            throw new ParseException(INVALID_EVENT_FORMAT_ERROR_MESSAGE);
        }
        String[] toSplit = fromSplit[1].split("/to");
        if (toSplit.length != 2) {
            throw new ParseException(INVALID_EVENT_FORMAT_ERROR_MESSAGE);
        }
        String[] commandAndDescription = fromSplit[0].trim().split(" ", 2);
        String description = commandAndDescription.length > 1 ? commandAndDescription[1] : "";
        String fromDateString = toSplit[0].trim();
        LocalDateTime fromDate = DateParser.getDateTimeString(fromDateString);
        String toDateString = toSplit[1].trim();
        LocalDateTime toDate = DateParser.getDateTimeString(toDateString);
        Task taskToAdd = new Event(description, false, fromDate, toDate, DateParser.hasTime(fromDateString));
        return new AddCommand(taskToAdd);
    }

}

