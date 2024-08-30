package Exceptions;

/**
 * The EmptyDescriptionException is thrown when a required description or input is missing.
 * This exception is typically used in commands that require additional details or parameters.
 */
public class EmptyDescriptionException extends Exception {

    /**
     * Constructs a new EmptyDescriptionException with the specified detail message.
     *
     * @param message The detail message explaining the reason for the exception.
     */
    public EmptyDescriptionException(String message) {
        super(message);
    }
}
