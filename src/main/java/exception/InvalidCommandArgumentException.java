package exception;

public class InvalidCommandArgumentException extends RatchetException {
    public InvalidCommandArgumentException(String message) {
        super("Invalid command argument: " + message);
    }
}
