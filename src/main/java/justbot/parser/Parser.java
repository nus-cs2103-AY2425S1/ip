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
            throw new JustbotException("Hey man you provided an invalid date and time format. Please use [dd/MM/yyyy HH:mm].");
        }
    }

    /**
     * Creates a new TodoCommand from the given array of words.
     * This method extracts and trims the description of the todo task from the user input.
     *
     * The first element in the array is expected to be the command type,
     * and the second element should contain the description of the todo task.
     *
     * Throws an exception if the command is not in the correct format.
     *
     * @param words An array of strings where the first element is the command type
     *              and the second element is the task description.
     * @return A new TodoCommand object containing the trimmed description.
     * @throws JustbotException If the todo command is invalid or improperly formatted.
     */
    private TodoCommand createTodoCommand(String[] words) throws JustbotException {
        validateTodoCommand(words);
        return new TodoCommand(words[1].trim());
    }

    /**
     * Validates the format of the todo command.
     * Ensures that the command contains exactly two elements and that the description
     * is not blank or empty.
     *
     * If the command does not meet the required format, an exception is thrown.
     *
     * @param words An array of strings where the first element is the command type
     *              and the second element is expected to be the description.
     * @throws JustbotException If the command is missing a description or if the description is blank.
     */
    private void validateTodoCommand(String[] words) throws JustbotException {
        assert words.length == 2 : "todo command needs a description";
        if (words.length < 2 || words[1].trim().isEmpty()) {
            throw new JustbotException("Hey man, the description for todo is blank!");
        }
    }

    /**
     * Creates a new DeadlineCommand from the given array of words.
     * This method validates the command format, extracts the description and deadline date,
     * and parses the deadline into a LocalDateTime object.
     *
     * The command is expected to be in the format: "deadline <description> /by <date-time>".
     *
     * Throws an exception if the command format is incorrect or if the deadline date is invalid.
     *
     * @param words An array of strings where the first element is the command type
     *              and the second element contains the description and deadline date.
     * @return A new DeadlineCommand object containing the description and deadline date.
     * @throws JustbotException If the command format is invalid or if the deadline date is not properly specified.
     */
    private DeadlineCommand createDeadlineCommand(String[] words) throws JustbotException {
        validateDeadlineCommandFormat(words);
        String[] deadlineParts = splitCommandDetails(words[1], "/by");
        String deadlineDescription = validateAndGetDescription(deadlineParts[0]);
        LocalDateTime byDateTime = parseDateTime(deadlineParts[1].trim());
        return new DeadlineCommand(deadlineDescription, byDateTime);
    }

    /**
     * Validates the format of the deadline command.
     * Ensures that the command contains exactly two elements: the command type and the details.
     *
     * Throws an exception if the command does not adhere to the expected format.
     * The expected format is: "deadline [task description] /by [dd/MM/yyyy HH:mm]".
     *
     * @param words An array of strings where the first element is the command type
     *              and the second element contains the task description and deadline date.
     * @throws JustbotException If the command does not have exactly two elements or is in an incorrect format.
     */
    private void validateDeadlineCommandFormat(String[] words) throws JustbotException {
        if (words.length != 2) {
            throw new JustbotException(
                    "Hey man you provided an invalid deadline command format. Use: deadline [task description] /by [dd/MM/yyyy HH:mm]");
        }
    }

    /**
     * Splits the command details into parts using the specified delimiter.
     * This method divides the details into an array of two elements based on the delimiter.
     *
     * If the delimiter is not found in the details, an exception is thrown.
     *
     * @param details The string containing the command details to be split.
     * @param delimiter The delimiter used to split the command details.
     * @return An array of strings with exactly two elements: the part before the delimiter and the part after.
     * @throws JustbotException If the delimiter is not present in the details, indicating an invalid command format.
     */
    private String[] splitCommandDetails(String details, String delimiter) throws JustbotException {
        String[] parts = details.split(delimiter, 2);
        if (parts.length < 2) {
            throw new JustbotException(
                    "Hey man the format is invalid. Remember to use the keyword: " + delimiter);
        }
        return parts;
    }

    /**
     * Validates and returns the trimmed description of a task.
     * Ensures that the description is not blank or empty.
     *
     * If the description is empty after trimming, an exception is thrown.
     *
     * @param description The description string to be validated and trimmed.
     * @return The trimmed description if it is not empty.
     * @throws JustbotException If the description is blank or empty.
     */
    private String validateAndGetDescription(String description) throws JustbotException {
        if (description.trim().isEmpty()) {
            throw new JustbotException("Hey man, you can't leave the description blank!");
        }
        return description.trim();
    }

    /**
     * Creates a new EventCommand from the given array of words.
     * This method validates the command format, extracts the event description, start, and end times,
     * and parses the times into LocalDateTime objects.
     *
     * The command is expected to be in the format: "event <description> /from <start-date-time> /to <end-date-time>".
     *
     * Throws an exception if the command format is incorrect, if the event description is blank,
     * or if the event timing is invalid.
     *
     * @param words An array of strings where the first element is the command type
     *              and the second element contains the description and event time details.
     * @return A new EventCommand object containing the description, start date-time, and end date-time.
     * @throws JustbotException If the command format is invalid, the description is blank, or the event timing is incorrect.
     */
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

    /**
     * Validates the format of the event command.
     * Ensures that the command contains at least two elements: the command type and the details.
     *
     * The expected format is: "event [task description] /from [dd/MM/yyyy HH:mm] /to [dd/MM/yyyy HH:mm]".
     * Throws an exception if the command does not include the necessary elements.
     *
     * @param words An array of strings where the first element is the command type
     *              and the second element contains the event details.
     * @throws JustbotException If the command does not have enough elements or is in an incorrect format.
     */
    private void validateEventCommandFormat(String[] words) throws JustbotException {
        if (words.length < 2) {
            throw new JustbotException(
                    "Hey man you provided an invalid event command format. Use: event [task description] "
                            + "/from [dd/MM/yyyy HH:mm] /to [dd/MM/yyyy HH:mm]");
        }
    }

    /**
     * Validates the timing of an event to ensure the start date-time is before the end date-time.
     *
     * Throws an exception if the start date-time is not before the end date-time.
     *
     * @param start The start date-time of the event.
     * @param end The end date-time of the event.
     * @throws JustbotException If the start date-time is not before the end date-time.
     */
    private void validateEventTiming(LocalDateTime start, LocalDateTime end) throws JustbotException {
        if (!start.isBefore(end)) {
            throw new JustbotException("Hey man, why is the end date and time before the start date and time?");
        }
    }

    /**
     * Creates a new FindCommand from the given array of words.
     * This method validates the find command format and extracts the keywords for searching.
     *
     * The command is expected to be in the format: "find [keywords]".
     *
     * Throws an exception if the command format is incorrect.
     *
     * @param words An array of strings where the first element is the command type
     *              and the second element contains the search keywords.
     * @return A new FindCommand object initialized with the extracted keywords.
     * @throws JustbotException If the find command format is invalid or if the keywords are missing.
     */
    private FindCommand createFindCommand(String[] words) throws JustbotException {
        validateFindCommand(words);
        String[] keywordsArr = extractKeywords(words[1]);
        return new FindCommand(keywordsArr);
    }

    /**
     * Validates the format of the find command.
     * Ensures that the command contains at least two elements: the command type and the search keywords.
     *
     * The expected format is: "find [task description]".
     * Throws an exception if the command does not include the necessary elements.
     *
     * @param words An array of strings where the first element is the command type
     *              and the second element contains the search keywords.
     * @throws JustbotException If the find command format is invalid or if the keywords are missing.
     */
    private void validateFindCommand(String[] words) throws JustbotException {
        if (words.length < 2) {
            throw new JustbotException("Hey man, follow the format:\n" + "find [task description]");
        }
    }

    /**
     * Extracts keywords from a given string by splitting it into individual words.
     * This method splits the input string at each space character and returns an array of keywords.
     *
     * @param keywords A string containing keywords separated by spaces.
     * @return An array of keywords extracted from the input string.
     */
    private String[] extractKeywords(String keywords) {
        return keywords.split(" ");
    }

    /**
     * Creates a new MarkCommand to mark a task as completed.
     * This method validates and parses the task number from the command input.
     *
     * The command is expected to be in the format: "mark [task number]".
     *
     * Throws an exception if the command format is incorrect or the task number is invalid.
     *
     * @param words An array of strings where the first element is the command type
     *              and the second element is the task number to be marked.
     * @return A new MarkCommand object initialized with the task number.
     * @throws JustbotException If the command format is invalid or the task number is not valid.
     */
    private MarkCommand createMarkCommand(String[] words) throws JustbotException {
        int markNumber = validateAndParseTaskNumber(words, "mark");
        return new MarkCommand(markNumber);
    }

    /**
     * Creates a new UnmarkCommand to mark a task as not completed.
     * This method validates and parses the task number from the command input.
     *
     * The command is expected to be in the format: "unmark [task number]".
     *
     * Throws an exception if the command format is incorrect or if the task number is invalid.
     *
     * @param words An array of strings where the first element is the command type
     *              and the second element is the task number to be unmarked.
     * @return A new UnmarkCommand object initialized with the task number.
     * @throws JustbotException If the command format is invalid or the task number is not valid.
     */
    private UnmarkCommand createUnmarkCommand(String[] words) throws JustbotException {
        int unmarkNumber = validateAndParseTaskNumber(words, "unmark");
        return new UnmarkCommand(unmarkNumber);
    }

    /**
     * Creates a new DeleteCommand to remove a task.
     * This method validates and parses the task number from the command input.
     *
     * The command is expected to be in the format: "delete [task number]".
     *
     * Throws an exception if the command format is incorrect or if the task number is invalid.
     *
     * @param words An array of strings where the first element is the command type
     *              and the second element is the task number to be deleted.
     * @return A new DeleteCommand object initialized with the task number.
     * @throws JustbotException If the command format is invalid or the task number is not valid.
     */
    private DeleteCommand createDeleteCommand(String[] words) throws JustbotException {
        int deleteNumber = validateAndParseTaskNumber(words, "delete");
        return new DeleteCommand(deleteNumber);
    }

    /**
     * Validates and parses the task number from the given command input.
     * This method checks if the task number is provided and if it's a positive integer.
     *
     * The command is expected to include the task number in the format: "[command] [task number]".
     *
     * Throws an exception if the command format is incorrect or if the task number is not a valid positive integer.
     *
     * @param words An array of strings where the first element is the command type
     *              and the second element is expected to be the task number.
     * @param command The command type (e.g., "mark", "unmark", "delete") to include in the error message.
     * @return The parsed task number as an integer.
     * @throws JustbotException If the task number is missing, not a valid integer, or is negative.
     */
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
