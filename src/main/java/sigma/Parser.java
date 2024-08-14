package sigma;

import sigma.command.CommandType;
import sigma.exception.SigmaMissingArgException;
import sigma.exception.SigmaUnknownCommandException;

/**
 * The {@code Parser} class is responsible for interpreting user input.
 * It identifies the command and extracts any arguments required for the execution of the command.
 */
public class Parser {
    /**
     * Parses the user's input to determine the command type.
     *
     * @param userInput The input provided by the user.
     * @return The {@code CommandType} corresponding to the user's input.
     * @throws SigmaUnknownCommandException If the command in the user's input is not recognized.
     */
    public static CommandType parseCommand(String userInput) throws SigmaUnknownCommandException {
        String command = userInput.split(" ", 2)[0].toUpperCase();
        if (!CommandType.isValidCommand(command)) {
            throw new SigmaUnknownCommandException(userInput);
        }
        return CommandType.valueOf(command);
    }

    /**
     * Extracts and returns the arguments from the user's input after the command.
     *
     * @param command The {@code CommandType} that has been parsed from the user's input.
     * @param userInput The full input provided by the user.
     * @return The arguments following the command in the user's input.
     * @throws SigmaMissingArgException If no arguments are provided after the command.
     */
    public static String parseArgs(CommandType command, String userInput) throws SigmaMissingArgException {
        try {
            return userInput.split(" ", 2)[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new SigmaMissingArgException(command);
        }

    }
}
