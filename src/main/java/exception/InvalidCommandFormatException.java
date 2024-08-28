package exception;

import enums.Command;

/**
 * Exception for an invalid command format.
 *
 * @author dwsc37.
 */
public class InvalidCommandFormatException extends BotException {
    /**
     * Constructor for an <code>InvalidCommandFormatException</code>.
     * @param command Command type inputted by the user.
     */
    public InvalidCommandFormatException(Command command) {
        super(InvalidCommandFormatException.generateMessage(command));
    }

    private static String generateMessage(Command commandType) {
        String message = "Invalid command format for " + commandType.getInput() + "!\n";
        message = message + "Usage: " + commandType.getUsage();
        return message;
    }
}
