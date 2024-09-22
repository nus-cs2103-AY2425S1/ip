package puke.exceptions;

/**
 * Exception thrown when input is empty or missing.
 */
public class EmptyInputException extends PukeException {

    /**
     * Constructs an EmptyInputException with a default message.
     */
    public EmptyInputException() {
        super("OOPS!!! You need to enter a command.");
    }

    /**
     * Constructs an EmptyInputException with a specified custom message.
     *
     * @param message the custom message for the exception
     */
    public EmptyInputException(String message) {
        super(message);
    }
}
