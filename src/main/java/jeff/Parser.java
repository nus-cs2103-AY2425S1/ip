package jeff;

import jeff.command.*;
import jeff.exceptions.JEFFException;

/**
 * Parses user input and returns the appropriate command.
 *
 * The Parser class is responsible for interpreting user commands and returning the corresponding Command object.
 */
public class Parser {

    /**
     * Parses the user's command input and returns the corresponding Command object.
     *
     * @param fullCommand The full command string input by the user.
     * @return The Command object corresponding to the user's input.
     * @throws JEFFException If the command is not recognized.
     */
    public static Command parse(String fullCommand) throws JEFFException {
        String[] parts = fullCommand.split(" ", 2);
        String commandWord = parts[0].toLowerCase();
        String args = parts.length > 1 ? parts[1] : "";

        switch (commandWord) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "print":
            return new PrintCommand(args);
        case "mark":
            return new MarkCommand(args);
        case "unmark":
            return new UnMarkCommand(args);
        case "delete":
            return new DeleteCommand(args);
        case "todo":
            return new ToDoCommand(args);
        case "deadline":
            return new DeadlineCommand(args);
        case "event":
            return new EventCommand(args);
        case "find":
            return new FindCommand(args);

        // Add more cases here for other commands
        default:
            throw new JEFFException("Unknown command!"); // Implement UnknownCommand class to handle unrecognized commands
        }
    }
}
