package totoro.parser;

import totoro.command.*;
import totoro.exception.TotoroMissingArgException;
import totoro.exception.TotoroUnknownCommandException;

/**
 * Represents the parser that interprets user input and converts it into commands
 */
public class Parser {
    /**
     * Parses the user input to determine the command type
     *
     * @param userCommand The user's raw input string
     * @return The command type extracted from the user input
     * @throws TotoroUnknownCommandException If the command is not recognised
     */
    public static CommandType parseCommand(String userCommand) throws TotoroUnknownCommandException {
        assert userCommand != null && !userCommand.isBlank();
        String command = userCommand.split(" ", 2)[0].toUpperCase();
        if (!CommandType.isValidCommand(command)) {
            throw new TotoroUnknownCommandException(userCommand);
        }
        assert !command.isEmpty();

        return CommandType.valueOf(command);
    }

    /**
     * Extracts the arguments from the user input based on its command type
     *
     * @param command The command type already parsed
     * @param userInput The user's raw input string
     * @return The arguments extracted from the user input
     * @throws TotoroMissingArgException If the command requires arguments and they are missing
     */
    public static String parseArgs(CommandType command, String userInput) throws TotoroMissingArgException {
        try {
            return userInput.split(" ", 2)[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new TotoroMissingArgException(command);
        }
    }
}