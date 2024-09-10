package bean.parser;

import bean.command.Command;
import bean.exception.EmptyDescriptionException;
import bean.exception.UnknownCommandException;

/**
 * Parses user input and generates the appropriate Command object.
 */
public class Parser {

    /**
     * Parses the user's input string and returns a Command object.
     * If the command is not recognised, throws an UnknownCommandException.
     *
     * @param input The user's input string.
     * @return A Command object corresponding to the user's input.
     * @throws UnknownCommandException If the command is not recognised.
     */
    public Command parseCommand(String input) throws UnknownCommandException, EmptyDescriptionException {
        String[] parts = input.split(" ", 2);
        String command = parts[0];
        String details = parts.length > 1 ? parts[1] : "";

        // Check if the command is recognized first
        switch (command) {
        case "list":
            return new Command("list");
        case "mark":
        case "unmark":
        case "delete":
        case "todo":
        case "deadline":
        case "event":
        case "find":
            // For all commands except 'list', ensure the description is not empty
            if (details.trim().isEmpty()) {
                throw new EmptyDescriptionException(command + " description cannot be empty.");
            }
            return new Command(command, details);
        default:
            // Throw an exception if the command is not recognized
            throw new UnknownCommandException();
        }
    }

}
