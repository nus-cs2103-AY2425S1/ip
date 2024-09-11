package stan;

import java.util.HashMap;
import java.util.Map;

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

    // Map to hold the command aliases
    private static final Map<String, CommandType> COMMAND_ALIASES = new HashMap<>();

    static {
        // Populate the map with aliases
        COMMAND_ALIASES.put("bye", CommandType.BYE);
        COMMAND_ALIASES.put("list", CommandType.LIST);
        COMMAND_ALIASES.put("ls", CommandType.LIST);
        COMMAND_ALIASES.put("todo", CommandType.TODO);
        COMMAND_ALIASES.put("t", CommandType.TODO); // Alias for todo
        COMMAND_ALIASES.put("event", CommandType.EVENT);
        COMMAND_ALIASES.put("e", CommandType.EVENT); // Alias for event
        COMMAND_ALIASES.put("deadline", CommandType.DEADLINE);
        COMMAND_ALIASES.put("d", CommandType.DEADLINE); // Alias for deadline
        COMMAND_ALIASES.put("mark", CommandType.MARK);
        COMMAND_ALIASES.put("unmark", CommandType.UNMARK);
        COMMAND_ALIASES.put("delete", CommandType.DELETE);
        COMMAND_ALIASES.put("del", CommandType.DELETE); // Alias for delete
        COMMAND_ALIASES.put("find", CommandType.FIND);
        COMMAND_ALIASES.put("f", CommandType.FIND); // Alias for find
    }

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
     * Converts a command string into the corresponding CommandType enum, considering aliases.
     *
     * @param command The command string.
     * @return The CommandType enum corresponding to the command string.
     * @throws StanInvalidCommandException If the command is not recognized.
     */
    private static CommandType getCommandType(String command) throws StanInvalidCommandException {
        if (command == null || command.isEmpty()) {
            throw new StanInvalidCommandException();
        }

        CommandType commandType = COMMAND_ALIASES.get(command.toLowerCase());
        if (commandType == null) {
            throw new StanInvalidCommandException();
        }

        return commandType;
    }
}
