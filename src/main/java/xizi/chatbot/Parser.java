package xizi.chatbot;

import xizi.chatbot.command.*;
import xizi.chatbot.task.TaskList;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


/**
 * The {@code Parser} class is responsible for parsing user input and returning
 * the corresponding command to be executed. It also provides utility methods
 * for date-time parsing and task number validation.
 */
public class Parser {
    /**
     * The date-time format used for parsing user input.
     */
    private static final DateTimeFormatter INPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

    /**
     * Parses the input string into a {@link LocalDateTime} object using the predefined format.
     *
     * @param dateTimeStr The date-time string to be parsed.
     * @return The parsed {@link LocalDateTime} object.
     * @throws DateTimeParseException if the input string does not match the expected format.
     */
    public static LocalDateTime parseDateTime(String dateTimeStr) throws DateTimeParseException {
        return LocalDateTime.parse(dateTimeStr, INPUT_DATE_FORMAT);
    }

    /**
     * Validates whether the given task number is within the valid range of the task list.
     *
     * @param taskNumber The task number to be validated.
     * @param actions The {@link TaskList} containing all tasks.
     * @throws XiziException if the task number is invalid.
     */
    public static void validateTaskNumber(int taskNumber, TaskList actions) throws XiziException {
        if (taskNumber < 0 || taskNumber >= actions.getSize()) {
            throw new XiziException("The task number does not exist. You have " + actions.getSize() + " tasks in total.");
        }
    }

    /**
     * Parses the user input and returns the appropriate {@link Command} object to execute.
     *
     * @param userInput The user input string.
     * @return The corresponding {@link Command} to be executed.
     * @throws IOException if an I/O error occurs during command execution.
     * @throws XiziException if the command is unrecognized or there is an error in parsing.
     */
    public Command parse(String userInput) throws IOException, XiziException {
        CommandType commandType = CommandType.fromInput(userInput);

        switch (commandType) {
        case DELETE:
            return new DeleteCommand(userInput);
        case MARK:
            return new MarkCommand(userInput);
        case UNMARK:
            return new UnmarkCommand(userInput);
        case TODO:
            return new TodoCommand(userInput);
        case DEADLINE:
            return new DeadlineCommand(userInput);
        case EVENT:
            return new EventCommand(userInput);
        case LIST:
            return new ListCommand();
        case BYE:
            return new ByeCommand();
        case LIST_ON:
            return new ListOnCommand(userInput);
        case HELP:
            return new HelpCommand();
        default:
            throw new XiziException("Sorry, I didn't understand that command. Type help for all available commands and format.");
        }
    }
}
