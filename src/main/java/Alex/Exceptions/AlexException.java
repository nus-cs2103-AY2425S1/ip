package Alex.Exceptions;

/**
 * Represents a general exception for the Alex application.
 * This is a custom exception that extends from the standard Java Exception class.
 */
public class AlexException extends Exception {

    /**
     * Constructs an AlexException with the specified detail message.
     * @param message The detail message.
     */
    public AlexException(String message) {
        super(message);
    }
}
