package colress.exception;

/**
 * An exception that is thrown when an empty input is entered by the user.
 */
public class EmptyInputException extends Exception {
    public EmptyInputException() {
        super("You need to input something. Try again.");
    }
}
