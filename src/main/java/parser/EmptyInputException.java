package parser;

/**
 * Exception that signifies empty input from user.
 */
public class EmptyInputException extends Exception {
    public EmptyInputException(String message) {
        super(message);
    }
}
