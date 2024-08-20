package hoodini;

/**
 * Exception class to throw exception when
 * there is an invalid input
 */
public class InvalidInputException extends Exception {
    public InvalidInputException(String message) {
        super(message);
    }
}
