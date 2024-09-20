package parser;

import command.AddCommand;
import command.Command;
import command.DeleteCommand;
import command.ExitCommand;
import command.FindCommand;
import command.ListCommand;
import command.MarkCommand;
import command.UnmarkCommand;
import command.UpdateCommand;
import exceptions.BuddyException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parses user input and returns the appropriate command.
 */
public class Parser {

    /**
     * Enum to represent the different types of commands.
     */
    enum CommandType {
        TODO, DEADLINE, EVENT, DELETE, MARK, UNMARK, BYE, LIST, FIND, UPDATE, UNKNOWN;
    }

    /**
     * Converts a string to the corresponding CommandType.
     *
     * @param command The input command string.
     * @return The corresponding CommandType.
     */
    public static CommandType fromString(String command) {
        return switch (command.toLowerCase()) {
            case "todo" -> CommandType.TODO;
            case "deadline" -> CommandType.DEADLINE;
            case "event" -> CommandType.EVENT;
            case "delete" -> CommandType.DELETE;
            case "mark" -> CommandType.MARK;
            case "unmark" -> CommandType.UNMARK;
            case "bye" -> CommandType.BYE;
            case "list" -> CommandType.LIST;
            case "find" -> CommandType.FIND;
            case "update" -> CommandType.UPDATE;
            default -> CommandType.UNKNOWN;
        };
    }

    /**
     * Parses the given command string and returns a corresponding Command object.
     *
     * @param command The user input command string.
     * @return The Command object corresponding to the user input.
     * @throws BuddyException If the command is not recognized or is invalid.
     */
    public static Command parse(String command) throws BuddyException {
        // Normalize the command string by trimming and replacing multiple spaces with a single space.
        String normalizedCommand = command.trim().replaceAll("\\s+", " ");

        // Split the command string into parts
        String[] commandParts = normalizedCommand.split(" ");
        String mainCommand = commandParts[0].toLowerCase();
        CommandType commandType = fromString(mainCommand);

        switch (commandType) {
        case TODO, DEADLINE, EVENT:
            return new AddCommand(normalizedCommand);
        case DELETE:
            return new DeleteCommand(parseTaskIndex(commandParts));
        case MARK:
            return new MarkCommand(parseTaskIndex(commandParts));
        case UNMARK:
            return new UnmarkCommand(parseTaskIndex(commandParts));
        case BYE:
            return new ExitCommand();
        case LIST:
            return new ListCommand();
        case FIND:
            String searchValue = commandParts[1].trim();
            return new FindCommand(searchValue);
        case UPDATE:
            return parseUpdateCommand(normalizedCommand);
        default:
            throw new BuddyException("Not too sure what command is that?");
        }
    }

    /**
     * Parses the update command, handling the quoted "new value".
     *
     * @param command The full command string for update.
     * @return The UpdateCommand object.
     * @throws BuddyException If the update command is malformed.
     */
    private static Command parseUpdateCommand(String command) throws BuddyException {
        // Regular expression to capture: update <Task Index> <Task Field> "<New Value>"
        Pattern pattern = Pattern.compile("update (\\d+) (\\w+) \"([^\"]+)\"");
        Matcher matcher = pattern.matcher(command);

        if (matcher.matches()) {
            int taskIndex = Integer.parseInt(matcher.group(1)) - 1; // Convert to 0-based index
            String field = matcher.group(2);
            String updatedValue = matcher.group(3);
            return new UpdateCommand(taskIndex, field, updatedValue);
        } else {
            throw new BuddyException("format: update <Task Index> <Task Field> \"<New Value>\" \n\n" +
                    "Options for task Field:\n1. Todo: description\n2. Deadline: description, deadline\n" +
                    "3. Event: description, start, end");
        }
    }

    /**
     * Parses the task index from the command parts.
     *
     * @param commandParts The array of command parts split by spaces.
     * @return The task index as an integer.
     * @throws BuddyException If the task index is not a valid number.
     */
    private static int parseTaskIndex(String[] commandParts) throws BuddyException {
        try {
            return Integer.parseInt(commandParts[1].trim()) - 1;
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new BuddyException("Invalid task index provided.");
        }
    }
}