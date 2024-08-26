package count.exception;

public class InvalidCommandException extends CountException {
    public InvalidCommandException() {
        super("I'm sorry, I did not understand that, type 'help' for available commands.");
    }
}
