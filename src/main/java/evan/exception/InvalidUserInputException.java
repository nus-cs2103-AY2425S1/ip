package evan.exception;

public class InvalidUserInputException extends EvanException {
    public InvalidUserInputException(String message) {
        super("Oops! That won't work - " + message);
    }
}
