package parser;

import command.AddCommand;
import command.FindCommand;
import command.ExitCommand;
import command.DeleteCommand;
import command.ListCommand;
import command.MarkCommand;
import command.UnmarkCommand;
import command.Command;
import exceptions.BuddyException;

/**
 * Parses user input and returns the appropriate command.
 */
public class Parser {

    /**
     * Enum to represent the different types of commands.
     */
    enum CommandType {
        TODO, DEADLINE, EVENT, DELETE, MARK, UNMARK, BYE, LIST, FIND, UNKNOWN;
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
        String[] commandParts = command.split(" ");
        String mainCommand = commandParts[0].toLowerCase();
        CommandType commandType = fromString(mainCommand);

        return switch (commandType) {
            case TODO, DEADLINE, EVENT -> new AddCommand(command);
            case DELETE -> new DeleteCommand(parseTaskIndex(commandParts));
            case MARK -> new MarkCommand(parseTaskIndex(commandParts));
            case UNMARK -> new UnmarkCommand(parseTaskIndex(commandParts));
            case BYE -> new ExitCommand();
            case LIST -> new ListCommand();
            case FIND -> new FindCommand(commandParts[1].trim());
            default -> throw new BuddyException("Unknown command.");
        };
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
            return Integer.parseInt(commandParts[1]) - 1;
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new BuddyException("Invalid task index provided.");
        }
    }
}