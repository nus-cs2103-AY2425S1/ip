package exception;

public class InvalidCommandException extends RatchetException {
    public InvalidCommandException(String message) {
        super("Invalid command: " + message);
    }
}
