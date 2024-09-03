package count.exception;

/**
 * InvalidCommandException is thrown when the command given is not recognised
 */
public class InvalidCommandException extends CountException {
    public InvalidCommandException() {
        super("I'm sorry, I did not understand that, type 'help' for available commands.");
    }
}
