package justbot.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import justbot.command.ByeCommand;
import justbot.command.Command;
import justbot.command.CommandType;
import justbot.command.DeadlineCommand;
import justbot.command.DeleteCommand;
import justbot.command.EventCommand;
import justbot.command.FindCommand;
import justbot.command.ListCommand;
import justbot.command.MarkCommand;
import justbot.command.TodoCommand;
import justbot.command.UnknownCommand;
import justbot.command.UnmarkCommand;
import justbot.exception.JustbotException;


/**
 * The Parser class is responsible for interpreting user input and converting it
 * into executable commands. It parses the input string, determines the command type,
 * and constructs the corresponding Command object.
 */
public class Parser {

    /**
     * Parses the user input to determine and create the appropriate command.
     *
     * @param userInput The input entered by the user.
     * @return The command created based on the parsed input.
     * @throws JustbotException If an error occurs during command parsing.
     */
    public Command parseCommand(String userInput) throws JustbotException {
        String[] words = userInput.split(" ", 2);
        CommandType commandType = CommandType.fromString(words[0].trim());
        assert commandType != null : "CommandType cannot be null";

        switch (commandType) {
        case TODO:
            return createTodoCommand(words);
        case DEADLINE:
            return createDeadlineCommand(words);
        case EVENT:
            return createEventCommand(words);
        case LIST:
            return new ListCommand();
        case FIND:
            return createFindCommand(words);
        case MARK:
            return createMarkCommand(words);
        case UNMARK:
            return createUnmarkCommand(words);
        case DELETE:
            return createDeleteCommand(words);
        case BYE:
            return new ByeCommand();
        case UNKNOWN:
        default:
            return new UnknownCommand();
        }
    }

    /**
     * Parses a date and time string into a {@code LocalDateTime} object.
     * The expected format for the date and time string is "dd/MM/yyyy HH:mm".
     *
     * @param dateTimeStr The date and time string to parse.
     * @return A {@code LocalDateTime} object representing the parsed date and time.
     * @throws JustbotException If the input string does not match the expected format.
     */
    public LocalDateTime parseDateTime(String dateTimeStr) throws JustbotException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            return LocalDateTime.parse(dateTimeStr.trim(), formatter);
        } catch (DateTimeParseException e) {
            throw new JustbotException("Invalid date and time format. Please use [dd/MM/yyyy HH:mm].");
        }
    }

    private TodoCommand createTodoCommand(String[] words) throws JustbotException {
        validateTodoCommand(words);
        return new TodoCommand(words[1].trim());
    }

    private void validateTodoCommand(String[] words) throws JustbotException {
        assert words.length == 2 : "todo command needs a description";
        if (words.length < 2 || words[1].trim().isEmpty()) {
            throw new JustbotException("Hey man, the description for todo is blank!");
        }
    }

    private DeadlineCommand createDeadlineCommand(String[] words) throws JustbotException {
        validateDeadlineCommandFormat(words);
        String[] deadlineParts = splitCommandDetails(words[1], "/by");
        String deadlineDescription = validateAndGetDescription(deadlineParts[0]);
        LocalDateTime byDateTime = parseDateTime(deadlineParts[1].trim());
        return new DeadlineCommand(deadlineDescription, byDateTime);
    }

    private void validateDeadlineCommandFormat(String[] words) throws JustbotException {
        if (words.length != 2) {
            throw new JustbotException(
                    "Invalid deadline command format. Use: deadline [task description] /by [dd/MM/yyyy HH:mm]");
        }
    }

    private String[] splitCommandDetails(String details, String delimiter) throws JustbotException {
        String[] parts = details.split(delimiter, 2);
        if (parts.length < 2) {
            throw new JustbotException(
                    "Invalid command format. Use the correct delimiter: " + delimiter);
        }
        return parts;
    }

    private String validateAndGetDescription(String description) throws JustbotException {
        if (description.trim().isEmpty()) {
            throw new JustbotException("Hey man, you can't leave the description blank!");
        }
        return description.trim();
    }

    private EventCommand createEventCommand(String[] words) throws JustbotException {
        validateEventCommandFormat(words);
        String[] eventParts = splitCommandDetails(words[1], "/from");
        String eventDescription = validateAndGetDescription(eventParts[0]);
        String[] timeParts = splitCommandDetails(eventParts[1], "/to");
        LocalDateTime startDateTime = parseDateTime(timeParts[0].trim());
        LocalDateTime endDateTime = parseDateTime(timeParts[1].trim());
        validateEventTiming(startDateTime, endDateTime);
        return new EventCommand(eventDescription, startDateTime, endDateTime);
    }

    private void validateEventCommandFormat(String[] words) throws JustbotException {
        if (words.length < 2) {
            throw new JustbotException(
                    "Invalid event command format. Use: event [task description] "
                            + "/from [dd/MM/yyyy HH:mm] /to [dd/MM/yyyy HH:mm]");
        }
    }

    private void validateEventTiming(LocalDateTime start, LocalDateTime end) throws JustbotException {
        if (!start.isBefore(end)) {
            throw new JustbotException("Hey man, why is the end date and time before the start date and time?");
        }
    }

    private FindCommand createFindCommand(String[] words) throws JustbotException {
        validateFindCommand(words);
        String[] keywordsArr = extractKeywords(words[1]);
        return new FindCommand(keywordsArr);
    }

    private void validateFindCommand(String[] words) throws JustbotException {
        if (words.length < 2) {
            throw new JustbotException("Hey man, follow the format:\n" + "find [task description]");
        }
    }

    private String[] extractKeywords(String keywords) {
        return keywords.split(" ");
    }

    private MarkCommand createMarkCommand(String[] words) throws JustbotException {
        int markNumber = validateAndParseTaskNumber(words, "mark");
        return new MarkCommand(markNumber);
    }

    private UnmarkCommand createUnmarkCommand(String[] words) throws JustbotException {
        int unmarkNumber = validateAndParseTaskNumber(words, "unmark");
        return new UnmarkCommand(unmarkNumber);
    }

    private DeleteCommand createDeleteCommand(String[] words) throws JustbotException {
        int deleteNumber = validateAndParseTaskNumber(words, "delete");
        return new DeleteCommand(deleteNumber);
    }

    private int validateAndParseTaskNumber(String[] words, String command) throws JustbotException {
        if (words.length < 2) {
            throw new JustbotException("Hey man, follow the format:\n" + command + " [task number]");
        }
        try {
            int number = Integer.parseInt(words[1].trim());
            assert number > 0 : command + " number cannot be negative";
            return number;
        } catch (NumberFormatException e) {
            throw new JustbotException("Hey man, follow the format:\n" + command + " [task number]");
        }
    }
}
