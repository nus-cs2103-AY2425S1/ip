package stan;

import stan.commands.Command;
import stan.commands.DeadlineCommand;
import stan.commands.DeleteCommand;
import stan.commands.EventCommand;
import stan.commands.ExitCommand;
import stan.commands.ListCommand;
import stan.commands.MarkCommand;
import stan.commands.TodoCommand;
import stan.commands.UnmarkCommand;
import stan.exceptions.StanException;
import stan.exceptions.StanInvalidArgumentException;
import stan.exceptions.StanInvalidCommandException;
import stan.exceptions.StanMissingArgumentException;

/**
 * The Parser class is responsible for parsing user commands.
 * It interprets the command and returns the appropriate Command object to be executed.
 */
public class Parser {

    /**
     * Parses the full command entered by the user and returns the corresponding Command object.
     *
     * @param fullCommand The full command string entered by the user.
     * @return The Command object corresponding to the user's command.
     * @throws StanException If the command is invalid or arguments are missing.
     */
    public static Command parse(String fullCommand) throws StanException {
        String[] words = fullCommand.split(" ", 2);
        CommandType commandType = getCommandType(words[0]);

        try {
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
            default:
                throw new StanInvalidCommandException();
            }
        } catch (StanMissingArgumentException | StanInvalidArgumentException e) {
            throw new StanException("Invalid input: " + e.getMessage());
        }
    }

    /**
     * Determines the type of command based on the user's input.
     *
     * @param command The command string entered by the user.
     * @return The CommandType corresponding to the user's command.
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
