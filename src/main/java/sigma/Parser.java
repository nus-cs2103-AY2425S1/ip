package sigma;

import sigma.command.CommandType;
import sigma.exception.SigmaMissingArgException;
import sigma.exception.SigmaUnknownCommandException;

public class Parser {
    public static CommandType parseCommand(String userInput) throws SigmaUnknownCommandException {
        String command = userInput.split(" ", 2)[0].toUpperCase();
        if (!CommandType.isValidCommand(command)) {
            throw new SigmaUnknownCommandException(userInput);
        }
        return CommandType.valueOf(command);
    }

    public static String parseArgs(CommandType command, String userInput) throws SigmaMissingArgException {
        try {
            return userInput.split(" ", 2)[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new SigmaMissingArgException(command);
        }

    }
}
