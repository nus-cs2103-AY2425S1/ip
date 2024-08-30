package parser;

import command.*;
import exceptions.BuddyException;

/**
 * Parses user input and returns the appropriate command.
 */
public class Parser {

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

        switch (mainCommand) {
            case "todo":
            case "deadline":
            case "event":
                return new AddCommand(command);
            case "delete":
                return new DeleteCommand(parseTaskIndex(commandParts));
            case "mark":
                return new MarkCommand(parseTaskIndex(commandParts));
            case "unmark":
                return new UnmarkCommand(parseTaskIndex(commandParts));
            case "bye":
                return new ExitCommand();
            case "list":
                return new ListCommand();
            case "find":
                return new FindCommand(commandParts[1].trim());
            default:
                throw new BuddyException("Unknown command.");
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
            return Integer.parseInt(commandParts[1]) - 1;
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new BuddyException("Invalid task index provided.");
        }
    }
}