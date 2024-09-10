package stan;

import stan.commands.Command;
import stan.commands.DeadlineCommand;
import stan.commands.DeleteCommand;
import stan.commands.EventCommand;
import stan.commands.ExitCommand;
import stan.commands.FindCommand;
import stan.commands.ListCommand;
import stan.commands.MarkCommand;
import stan.commands.TodoCommand;
import stan.commands.UnmarkCommand;
import stan.exceptions.StanException;
import stan.exceptions.StanInvalidCommandException;

/**
 * Parser class to interpret user commands.
 */
public class Parser {

    /**
     * Parses the user input and returns the corresponding Command object.
     *
     * @param fullCommand The full user command as a string.
     * @return The Command object representing the user command.
     * @throws StanException If the command is invalid or has missing/invalid arguments.
     */
    public static Command parse(String fullCommand) throws StanException {
        // Assert that the fullCommand is not null and not empty
        assert fullCommand != null : "fullCommand should not be null";
        assert !fullCommand.trim().isEmpty() : "fullCommand should not be empty";

        String[] words = fullCommand.split(" ", 2);
        // Assert that words array is not empty
        assert words.length > 0 : "words array should contain at least one element";
        CommandType commandType = getCommandType(words[0]);

        switch (commandType) {
        case BYE:
            return new ExitCommand();
        case LIST:
            return new ListCommand();
        case MARK:
            return new MarkCommand(words);
        case UNMARK:
            return new UnmarkCommand(words);
        case TODO:
            return new TodoCommand(words);
        case DEADLINE:
            return new DeadlineCommand(words);
        case EVENT:
            return new EventCommand(words);
        case DELETE:
            return new DeleteCommand(words);
        case FIND:
            return new FindCommand(words);
        default:
            throw new StanInvalidCommandException();
        }
    }

    /**
     * Converts a command string into the corresponding CommandType enum.
     *
     * @param command The command string.
     * @return The CommandType enum corresponding to the command string.
     * @throws StanInvalidCommandException If the command is not recognized.
     */
    private static CommandType getCommandType(String command) throws StanInvalidCommandException {
        try {
            return CommandType.valueOf(command.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new StanInvalidCommandException();
        }
    }
}
