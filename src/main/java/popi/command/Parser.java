package popi.command;

import popi.exception.PopiException;
import popi.exception.UnknownCommandException;

/**
 * Represents a parser that parses the input and returns the corresponding Command object.
 */
public class Parser {

    /**
     * Parses the input and returns the corresponding Command object.
     * @param input The input to be parsed.
     * @return The Command object corresponding to the input.
     * @throws PopiException If the input is invalid.
     */
    public static Command parse(String input) throws PopiException {
        if (input == null || input.trim().isEmpty()) {
            throw new UnknownCommandException();
        }

        String[] parts = input.split(" ", 2);
        String command = parts[0];

        return switch (command.toLowerCase()) {
        case "bye" -> new ExitCommand();
        case "list" -> new ListCommand();
        case "mark" -> new MarkCommand(input);
        case "unmark" -> new UnmarkCommand(input);
        case "delete" -> new DeleteCommand(input);
        case "todo", "deadline", "event" -> new AddCommand(input);
        case "find" -> new FindCommand(input);
        case "help" -> new HelpCommand();
        default -> throw new UnknownCommandException();
        };
    }
}
