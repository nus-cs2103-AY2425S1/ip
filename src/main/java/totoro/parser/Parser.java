package totoro.parser;

import totoro.command.*;
import totoro.exception.TotoroMissingArgException;
import totoro.exception.TotoroUnknownCommandException;

/**
 * Represents the parser that interprets user input and converts it into commands
 */
public class Parser {
    public static CommandType parseCommand(String userCommand) throws TotoroUnknownCommandException {
        assert userCommand != null && !userCommand.isBlank();
        String command = userCommand.split(" ", 2)[0].toUpperCase();
        if (!CommandType.isValidCommand(command)) {
            throw new TotoroUnknownCommandException(userCommand);
        }
        assert !command.isEmpty();

        return CommandType.valueOf(command);
    }

    public static String parseArgs(CommandType command, String userInput) throws TotoroMissingArgException {
        try {
            return userInput.split(" ", 2)[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new TotoroMissingArgException(command);
        }
    }
}