package exception;

public class InvalidCommandException extends BotException {
    public InvalidCommandException(String input) {
        super("Invalid command: " + input + "! Type 'help' to view all available commands." );
    }
}
