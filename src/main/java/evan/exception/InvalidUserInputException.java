package evan.exception;

public class InvalidUserInputException extends Exception {
    public InvalidUserInputException(String message) {
        super("Oops! That won't work - " + message);
    }
}
