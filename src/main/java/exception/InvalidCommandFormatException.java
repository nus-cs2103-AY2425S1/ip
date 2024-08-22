package exception;

import enums.Command;

public class InvalidCommandFormatException extends BotException {
    public InvalidCommandFormatException(Command command) {
        super(InvalidCommandFormatException.generateMessage(command));
    }

    private static String generateMessage(Command commandType) {
        String message = "Invalid command format for " + commandType.getInput() + "!\n";
        message = message + "Usage: " + commandType.getUsage();
        return message;
    }
}
