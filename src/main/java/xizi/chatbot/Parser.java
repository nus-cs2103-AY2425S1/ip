package xizi.chatbot;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import xizi.chatbot.command.ByeCommand;
import xizi.chatbot.command.Command;
import xizi.chatbot.command.CommandType;
import xizi.chatbot.command.DeadlineCommand;
import xizi.chatbot.command.DeleteCommand;
import xizi.chatbot.command.DeleteDoneTasksCommand;
import xizi.chatbot.command.EventCommand;
import xizi.chatbot.command.FindCommand;
import xizi.chatbot.command.HelpCommand;
import xizi.chatbot.command.ListCommand;
import xizi.chatbot.command.ListOnCommand;
import xizi.chatbot.command.MarkCommand;
import xizi.chatbot.command.RemoveTagCommand;
import xizi.chatbot.command.SearchByTagCommand;
import xizi.chatbot.command.TagCommand;
import xizi.chatbot.command.TodoCommand;
import xizi.chatbot.command.UnmarkCommand;
import xizi.chatbot.task.TaskList;


/**
 * The {@code Parser} class provides utility methods for parsing user input,
 * validating task numbers, and converting date-time strings to {@code LocalDateTime} objects.
 * It also interprets user commands and creates the corresponding {@code Command} instances.
 */
public class Parser {
    private static final DateTimeFormatter INPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

    /**
     * Parses a date-time string into a {@code LocalDateTime} object.
     *
     * @param dateTimeStr The date-time string to parse.
     * @return The parsed {@code LocalDateTime} object.
     * @throws DateTimeParseException If the date-time string cannot be parsed.
     */
    public static LocalDateTime parseDateTime(String dateTimeStr) throws DateTimeParseException {
        return LocalDateTime.parse(dateTimeStr, INPUT_DATE_FORMAT);
    }

    /**
     * Validates the given task number to ensure it is within the valid range of tasks.
     *
     * @param taskNumber The task number to validate.
     * @param actions    The task list containing all tasks.
     * @throws XiziException If the task number is invalid.
     */
    public static void validateTaskNumber(int taskNumber, TaskList actions) throws XiziException {
        if (taskNumber < 0 || taskNumber >= actions.getSize()) {
            throw new XiziException("The task number does not exist. You have "
                    + actions.getSize() + " tasks in total.");
        }
    }


    /**
     * Parses the user input to create the appropriate {@code Command} instance.
     *
     * @param userInput The user input string to parse.
     * @return The corresponding {@code Command} instance.
     * @throws IOException   If an I/O error occurs during command creation.
     * @throws XiziException If the user input does not match any known command format.
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
        case FIND:
            return new FindCommand(userInput);
        case DELETE_DONE:
            return new DeleteDoneTasksCommand();
        case TAG:
            return new TagCommand(userInput);
        case REMOVE_TAG:
            return new RemoveTagCommand(userInput);
        case SEARCH_TAG:
            return new SearchByTagCommand(userInput);
        default:
            throw new XiziException("Sorry, I didn't understand that command."
                    + "Type help for all available commands and format.");
        }
    }


}
