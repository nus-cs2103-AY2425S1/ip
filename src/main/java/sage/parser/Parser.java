package sage.parser;

import sage.command.*;
import sage.exception.SageException;

/**
 * Represents the parser that interprets user input and converts it into commands
 */
public class Parser {
    public static CommandType parseCommand(String userCommand) throws SageException {
        assert userCommand != null && !userCommand.isBlank();
        String command = userCommand.split(" ", 2)[0].toUpperCase();
        if (!CommandType.isValidCommand(command)) {
            throw new SageException("What are you trying to do? ;)");
        }
        assert !command.isEmpty();

        return CommandType.valueOf(command);
    }

    public static String parseArgs(CommandType command, String userInput) throws SageException {
        try {
            return userInput.split(" ", 2)[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new SageException("What do you mean? :p");
        }
    }
}